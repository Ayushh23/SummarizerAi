package com.example.SummarizerAI.service;

import com.example.SummarizerAI.entities.ContentEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class SummarizerService {
    @Value("${gemini.api.url}")
    private String geminiUrl;

    @Value("${gemini.api.key}")
    private String geminikey;


    private final WebClient webClient;

    public SummarizerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String processContent(ContentEntity content){
        String prompt =buildPrompt(content);


        Map<String,Object> requestBody=Map.of(
                "contents",new Object[]{
                        Map.of(
                                "parts",new Object[]{
                                        Map.of("text",prompt)
                                })
                }
        );
        String apiUrl = geminiUrl + "?key=" + geminikey;
        String response=webClient.post()
                .uri(apiUrl)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return extractReponseContent(response);
    }

    private String extractReponseContent(String response){
        try{
            ObjectMapper mapper=new ObjectMapper();
            JsonNode rootNode= mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        }
        catch (Exception e){
            return "Error"+e.getMessage();
        }
    }



    private  String buildPrompt(ContentEntity content){
        StringBuilder prompt=new StringBuilder();

        switch (content.getOperations()){
            case "summarize":
                prompt.append( "Summarize the following text in simple, clear, layman-friendly language.\n" +
                        "Guidelines:\n" +
                        "- Use plain text only (no bullet points, no numbering, no stars, no markdown).\n" +
                        "- Write in short, easy-to-read paragraphs.\n" +
                        "- Keep the meaning accurate but simplify complex words.\n" +
                        "- Do not add extra information.\n" +
                        "- Do not include headings or labels.\n\n");
                break;
            case "suggest":
                prompt.append("\"Based on the following content, suggest related topics and further reading.\\n\" +\n" +
                        "            \"Guidelines:\\n\" +\n" +
                        "            \"- Use short bullet points.\\n\" +\n" +
                        "            \"- Each point should be clear and beginner-friendly.\\n\" +\n" +
                        "            \"- Do not repeat the original content.\\n\" +\n" +
                        "            \"- Avoid technical jargon unless necessary.\\n\\n\"");
                break;
            default:
                throw new IllegalArgumentException("Unknown Operation "+content.getOperations());
        }

        prompt.append(content.getContent());

        return prompt.toString();

    }
}
