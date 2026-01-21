document.addEventListener('DOMContentLoaded',()=>{
    chrome.storage.local.get(['researchNotes'],function(result){
        if(result.researchNotes){
            document.getElementById('notes').value = result.researchNotes;
        }
    });

    document.getElementById('summarizeBtn').addEventListener('click',summarizeText);
    document.getElementById('saveNotesBtn').addEventListener('click',saveNotes);
});
async function summarizeText() {
    try {
        const[tab]=await chrome.tabs.query({active:true,currentWindow: true});
        const[{result}]=await chrome.scripting.executeScript({
            target:{tabId: tab.id},
            function :()=> window.getSelection().toString()
        })
        if(!result){
            showResult("Please select a paragraph for summarizing it ")
            return
        }
        const response= await fetch("http://localhost:8080/api/response",
            {
                method:'Post',
                headers:{'Content-Type': 'application/json'},
                body:JSON.stringify({content:result,operations:'summarize'})
            }
        );

        if(!response.ok){
            throw new Error(`API error : ${response.status}`);
        }

        const text= await response.text();
        const refinedText = refineResponse(text);

        showResult(refinedText);



    } catch (error) {
        showResult('Error: '+ error.message);
    }
}

async function saveNotes() {
    const notes =document.getElementById('notes').value;
    chrome.storage.local.set({'researchNotes':notes},function(){
        alert('Notes saved')
    })
    
}

function showResult(content){
  document.getElementById('result').innerHTML=`<div class="result-item"> <div class="result-content">${content}</div></div>`;
  
}
function refineResponse(text) {
  return text
    .replace(/^\*\s+/gm, '')         
    .replace(/\n{2,}/g, '\n\n')      
    .trim();
}
