window.addEventListener('load', function () {
    (function(){

      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(paciente of data){
            var table = document.getElementById("pacienteTable");
            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + Pacientes.id;
            pacienteRow.id = tr_id;


            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + Pacientes.id + '\"' +
                                      ' type="button" onclick="deleteBy('+ Pacientes.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';


            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + Pacientes.id + '\"' +
                                      ' type="button" onclick="findBy('+ Pacientes.id+')" class="btn btn-info btn_id">' +
                                      Pacientes.id +
                                      '</button>';


            pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + Pacientes.nombre + '</td>' +
                    '<td class=\"td_apellido\">' + Pacientes.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + Pacientes.dni + '</td>' +
                    '<td class=\"td_fechaIngreso\">' + Pacientes.fechaIngreso + '</td>' +
                    '<td class=\"td_domicilio\"> ' + Pacientes.domicilio.calle + ' ' + Pacientes.domicilio.numero + ', '+ Pacientes.domicilio.localidad + ', ' + Pacientes.domicilio.provincia + '</td>' +

                    '<td>' + deleteButton + '</td>';
        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/listarPacientes.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })