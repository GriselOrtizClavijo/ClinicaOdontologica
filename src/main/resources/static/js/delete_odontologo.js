$(document).ready(function () {
    let odontologoId = 0;

    $(document).on("click", "#div_odontologo_table table button.btn_delete", function () {
        let btn_id = (event.srcElement.id);
        odontologoId = btn_id.split("_")[2];

        $("div.modal-body")
            .text("¿Querés eliminar el odontólogo con ID = " + odontologoId + "?");
        $("#model-delete-btn").css({ "display": "inline" });
    });

    $(document).on("click", "#model-delete-btn", function () {
        $.ajax({
            url: '/odontologos/' + odontologoId,
            type: 'DELETE',
            success: function (response) {
                $("div.modal-body")
                    .text("Eliminado correctamente");

                $("#model-delete-btn").css({ "display": "none" });
                $("button.btn.btn-secondary").text("Close");

                let row_id = "tr_" + odontologoId;
                $("#" + row_id).remove();
                $("#div_odontologo_updating").css({ "display": "none" });
            },
            error: function (error) {
                console.error(JSON.stringify(error))
                $("#div_odontologo_updating").css({ "display": "none" });
                alert("Error -> " + error);
            }
        });
    });
});

