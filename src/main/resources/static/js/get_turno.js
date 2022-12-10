window.addEventListener('load', function () {
    (function(){
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(turno of data){

            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + Turnos.id;
            turnoRow.id = tr_id;

            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + Turnos.id + '\"' +
                                      ' type="button" onclick="deleteBy('+Turnos.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' +Turnos.id + '\"' +
                                      ' type="button" onclick="findBy('+Turnos.id+')" class="btn btn-info btn_id">' +
                                      Turnos.id +
                                      '</button>';

            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_fecha\">' + Turnos.date + '</td>' +
                    '<td class=\"td_paciente_id\">' + Turnos.paciente.apellido.toUpperCase() + ', ' + Turnos.paciente.nombre + '</td>' +
                    '<td class=\"td_odontologo_id\">' + Turnos.odontologo.apellido.toUpperCase() + ', '+ Turnos.odontologo.nombre + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };
    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/listarTurnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
    })