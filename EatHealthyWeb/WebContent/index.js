(function () {
	debugger;
  var uploader = document.createElement('input'),
    image = document.getElementById('img-result');

  uploader.type = 'file';
  uploader.accept = 'image/*';

  image.onclick = function() {
    uploader.click();
  }

  uploader.onchange = function() {
    var reader = new FileReader();
    reader.onload = function(evt) {
      image.classList.remove('no-image');
      image.style.backgroundImage = 'url(' + evt.target.result + ')';
      var request = {
        itemtype: 'test 1',
        brand: 'test 2',
        images: [{
          data: evt.target.result
        }]
      };
      
      document.querySelector('.show-button').style.display = 'block';
      document.querySelector('.upload-result__content').innerHTML = JSON.stringify(request, null, '  ');
    }
    reader.readAsDataURL(uploader.files[0]);
  }
  
  document.querySelector('.hide-button').onclick = function () {
    document.querySelector('.upload-result').style.display = 'none';
  };
  
  document.querySelector('.show-button').onclick = function () {
    document.querySelector('.upload-result').style.display = 'block';
  };
})();

//Attach a submit handler to the form
$("#requests").submit(function( event ) {

    // Stop form from submitting normally
    event.preventDefault();

    var uploader = document.createElement('input'),
    image = document.getElementById('img-result');

  uploader.type = 'file';
  uploader.accept = 'image/*';

  image.onclick = function() {
    uploader.click();
  }

  uploader.onchange = function() {
    var reader = new FileReader();
    reader.onload = function(evt) {
      image.classList.remove('no-image');
      image.style.backgroundImage = 'url(' + evt.target.result + ')';
      var request = {
        images: [{
          data: evt.target.result
        }]
      };
      
      document.querySelector('.show-button').style.display = 'block';
      document.querySelector('.upload-result__content').innerHTML = JSON.stringify(request, null, '  ');
    }
    reader.readAsDataURL(uploader.files[0]);
  }
  
    // Get some values from elements on the page:
    var $form = $( this );

    // We want to customize what we post, therefore we format our data
    var data = "image="+ document.querySelector('.upload-result');

    // For debugging purposes... see your console:
    // Prints out for example: login=myLoginName&passwordHash=a011a78a0c8d9e4f0038a5032d7668ab
    console.log(data);

    // The actual from POST method
    $.ajax({
        type: $form.attr('method'),
        url:  $form.attr('action'),
        data: data,
        success: function (data) {
            console.log("Hey, we got reply form java side, with following data: ");
            console.log(data);

            // redirecting example..
            if(data === "SUCCESS") {

               window.location.replace("UploadImage.html");

            }

        }
    });

});    

function processRequest()
{
    var uploader = document.createElement('input');
    uploader.type = 'file';
    uploader.accept = 'image/*';
    uploader.innerHTML = document.querySelector('.upload-result');
    // Submit the form using javascript
    var form = document.getElementById("requests");
    form.submit();
    
    //jQuery
    /* $("#vehicles").submit(); */
}