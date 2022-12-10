window.addEventListener('load', function () {

     const formulario = document.querySelector('#add_new_turno');

     formulario.addEventListener('submit', function (event) {
       event.preventDefault();

          const formData = {
                   date: document.querySelector('#fecha').value,
                   hora: document.querySelector('#hora').value,
                   paciente: {
                     id: document.querySelector('#paciente_id').value
                   },
                   odontologo: {
                     id: document.querySelector('#odontologo_id').value
                   }
               };
               console.log(formData)

         const url = '/turnos';
         const settings = {
             method: 'POST',
             headers: {
                 'Content-Type': 'application/json',
             },
             body: JSON.stringify(formData)
         }

         fetch(url, settings)
             .then(response => response.json())
             .then(data => {
                  let successAlert = '<div class="alert alert-success alert-dismissible">' +
                      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                      '<strong></strong> Turno agregado </div>'

                  document.querySelector('#response').innerHTML = successAlert;
                  document.querySelector('#response').style.display = "block";
                  resetUploadForm();

             })
             .catch(error => {

                     let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                      '<strong> Error intente nuevamente</strong> </div>'

                       document.querySelector('#response').innerHTML = errorAlert;
                       document.querySelector('#response').style.display = "block";
                      resetUploadForm();})
     });


     function resetUploadForm(){
           document.querySelector('#fecha').value = "";
           document.querySelector('#hora').value = "";
           document.querySelector('#paciente_id').value = "";
           document.querySelector('#odontologo_id').value = "";
       }

     (function(){
         let pathname = window.location.pathname;
         if(pathname === "/"){
             document.querySelector(".nav .nav-item a:first").addClass("active");
         } else if (pathname == "/turnos.html") {
             document.querySelector(".nav .nav-item a:last").addClass("active");
         }
     })();
 });

  /*$(document).ready(function() {
      $("#add_new_turno").submit(function(evt) {
          evt.preventDefault();

          let formData = {
              fecha : $("#fecha").val(),
              hora :  $("#hora").val(),
              paciente: $("#paciente_id").val(),
              odontologo: $("#odontologo_id").val(),
          }

          $.ajax({
              url: '/turnos',
              type: 'POST',
              contentType : "application/json",
              data: JSON.stringify(formData),
              dataType : 'json',
              async: false,
              cache: false,
              success: function (response) {
                  let turno = response
                 console.log(response)
                  let successAlert = '<div class="alert alert-success alert-dismissible">' +
                      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                      '<strong></strong> Turno agregado </div>'
                  $("#response").append(successAlert);
                  $("#response").css({"display": "block"});

                  resetUploadForm();
              },
              error: function (response) {
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                      '<strong> Error intente nuevamente</strong> </div>'
                  $("#response").append(errorAlert);
                  $("#response").css({"display": "block"});

                  resetUploadForm();
              }
          });
      });

      function resetUploadForm(){
        $("#fecha").val("");
        $("#hora").val("");
        $("#paciente_id").val("");
        $("#odontologo_id").val("");
      }

      (function(){
          let pathname = window.location.pathname;
          if(pathname === "/"){
              $(".nav .nav-item a:first").addClass("active");
          } else if (pathname == "/listarTurnos.html") {
              $(".nav .nav-item a:last").addClass("active");
          }
      })();
  });*/