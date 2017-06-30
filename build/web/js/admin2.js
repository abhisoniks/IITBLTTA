    function addNewlevel(source){
        var temp = document.getElementById('add')
        if(temp.style.display=="none"){
            temp.style.display = "block";
        }
        else{
            temp.style.display = "none";
        }
        var temp = document.getElementById('buttonClicked');
        temp.value = source.value; 
    }

    function modifyLevel(source){
            var temp = document.getElementById('buttonClicked'); 
            temp.value=source.value;    
            document.getElementById('adminradio').style.display="block";
    }

    function deleteLevel(source){
             var temp = document.getElementById('buttonClicked');
             temp.value = source.value;          
    }  

    function activateText(source){
       var temp = document.getElementById('period'+source.value);
       temp.removeAttribute('readonly')
    }   
    
    function validateNum(evt) {
        var theEvent = evt || window.event;
        var key = theEvent.keyCode || theEvent.which;
        key = String.fromCharCode( key );
        var regex = /[A-Za-z]|\./;
        if( regex.test(key) ) {
          theEvent.returnValue = false;
          if(theEvent.preventDefault) theEvent.preventDefault();
        }
    }


