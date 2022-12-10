$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "/odontologos",
            success: function(response){
              $.each(response, (i, Odontologos) => {
                let deleteButton = '<button ' +
                              'id=' +
                              '\"' + 'btn_delete_' + Odontologos.id + '\"'+
                              ' type="button" class="btn btn-danger btn_delete" data-toggle="modal"  data-target="#delete-modal"' +
                              '>&times</button>';

                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + Odontologos.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' + 
                                            Odontologos.id +
                                            '</button>';
                
                let tr_id = 'tr_' + Odontologos.id;
                let odontologoRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_first_name\">'+ Odontologos.nombre.toUpperCase() + '</td>' +
                          '<td class=\"td_last_name\">' + Odontologos.apellido + '</td>' +
                          '<td class=\"td_matricula\">' + Odontologos.matricula + '</td>' +
                          '<td>' + deleteButton + '</td>' +
                          '</tr>';                
                $('#odontologoTable tbody').append(odontologoRow);
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    })();        
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/listarOdontologos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});