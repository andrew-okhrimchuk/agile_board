/**
 * Created by Disturbed on 11/28/2017.
 */
$(function(){
    var addButtons = $('.addButton');
    var layer = $('#layer');
    var container = $('.container');
    var columnName = $('#columnName');
    var table = $('#table');
    var container2 = $('#container2');

    addButtons.click(function(){
        var name = $(this).parent().attr('id');
        columnName.attr("value",name.toString());
        container.css('z-index',0);
        container.css('z-index',20);
        container.show();
        layer.fadeIn('fast');
    });

    layer.click(function(){
        container.hide();
        $(this).fadeOut('fast');
    });

    $('#newTicketForm').submit(function(e){
        event.preventDefault();
        var m_method = $(this).attr('method');
        var m_action = $(this).attr('action');
        var m_data = $(this).serialize();
        $.ajax({
            type: m_method,
            url: m_action,
            data: m_data,
            success: function(response){
                switch (columnName.val()){
                    case "TO DO":
                        $('#table > tbody:last').append('<tr><th><div class="container2" "><form action="">' +
                            '<div class="row"><div class="col-25"><label for="name2">Name</label>' +
                            '</div><div class="col-75"><input id = "inputName2" type="text" id="name2" name="name2" disabled/>' +
                            '</div></div><div class="row"><div class="col-25">' +
                            '<label for="description1">Description</label> </div><div class="col-75">' +
                            '<textarea id="description1" name="description1" style = "height: 75px" disabled="disabled"></textarea>' +
                            ' </div></div></form></div></th><th></th><th></th></tr>');
                        $('#inputName2').attr('value', response.name);
                        $('#description1').text(response.description);
                        break;

                    case "In Progress":
                        $('#table > tbody:last').append('<tr><th></th>><th><div class="container2" "><form action="">' +
                            '<div class="row"><div class="col-25"><label for="name2">Name</label>' +
                            '</div><div class="col-75"><input id = "inputName2" type="text" id="name2" name="name2" disabled/>' +
                            '</div></div><div class="row"><div class="col-25">' +
                            '<label for="description1">Description</label> </div><div class="col-75">' +
                            '<textarea id="description1" name="description1" style = "height: 75px" disabled="disabled"></textarea>' +
                            ' </div></div></form></div></th><th></th></tr>');
                        $('#inputName2').attr('value', response.name);
                        $('#description1').text(response.description);
                        break;

                    case "Done":
                        $('#table > tbody:last').append('<tr><th></th><th></th><th><div class="container2" "><form action="">' +
                            '<div class="row"><div class="col-25"><label for="name2">Name</label>' +
                            '</div><div class="col-75"><input id = "inputName2" type="text" id="name2" name="name2" disabled/>' +
                            '</div></div><div class="row"><div class="col-25">' +
                            '<label for="description1">Description</label> </div><div class="col-75">' +
                            '<textarea id="description1" name="description1" style = "height: 75px" disabled="disabled"></textarea>' +
                            ' </div></div></form></div></th></tr>');
                        $('#inputName2').attr('value', response.name);
                        $('#description1').text(response.description);
                        break;
                }
                container.hide();
                layer.fadeOut('fast');
                alert(columnName.val());


            }

        });
    });


});