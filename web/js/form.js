function showrepeat(){
    var temp = document.getElementById('welcomeDiv')
    if(temp.style.display=="none"){
        temp.style.display = "block";
    }
    else{
        temp.style.display = "none";
    }    
    
}

function toggle(source) {  
  checkboxes = document.getElementsByName('day');
   for(var i=0, n=checkboxes.length;i<n;i++) {
    checkboxes[i].checked = source.checked;
  }
}

function toggleMonth(source) {
  checkboxes = document.getElementsByName('month');
   for(var i=0, n=checkboxes.length;i<n;i++) {
    checkboxes[i].checked = source.checked;
  }
}


function showDay(select){
    var selected = select.value;
    var temp = document.getElementById("dayCheck");
    var temp2 = document.getElementById("monthCheck");
    if(selected === 'weekly'){
        temp = document.getElementById("dayCheck");
        temp.style.display = ""
    }
    else if(selected === 'monthly'){
        temp.style.display = "none"
        temp2.style.display = ""
    }else{
        temp.style.display = "none"
        temp2.style.display = "none"
    }  
}

function showQuery(){
    alert('hi');
}