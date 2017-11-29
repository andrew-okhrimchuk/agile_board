/**
 * Created by Disturbed on 11/28/2017.
 */
$(function(){
    var clickCount = 0;
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
        var error = $('#errorBlock');
        $.ajax({
            type: m_method,
            url: m_action,
            data: m_data,
            success: function(response){
                if(response == "false"){
                    error.show();
                } else{
                    window.location="http://localhost:8080/greeting";
                }

            }

        });
    });

    $('.deleteButton').click(function (e){
            var formToSubmit =  $(this).parent().parent().parent();
            formToSubmit.attr('action', "/deleteticket");
            formToSubmit.find('input[name = "name"]').removeAttr('disabled');
            formToSubmit.submit(function (e){
                var m_method = formToSubmit .attr('method');
                var m_action = formToSubmit .attr('action');
                var m_data  = formToSubmit.serialize();
                $.ajax({
                    type: m_method,
                    url: m_action,
                    data: m_data,
                    success: function(response){
                            window.location="http://localhost:8080/greeting";
                    }

                });

            });
    });

    $('.editButton').click(function(e){
        clickCount ++;
        event.preventDefault();
        var formToSubmit =  $(this).parent().parent().parent();
        var saveButton =  formToSubmit.find('input[value = "Save"]').show();
        var description = formToSubmit.find('textarea');
        formToSubmit.attr('action', "/editticket");
        formToSubmit.find('input[name = "name"]').removeAttr('disabled');
        description.removeAttr('disabled');
        formToSubmit.find('input[value = "Delete"]').attr('disabled',"disabled");
        formToSubmit.find('input[value = "Move to"]').attr('disabled',"disabled");
        var editButton = formToSubmit.find('input[value = "Edit"]');
        var text = description.val();
        if( clickCount == 2) {
            window.location = "http://localhost:8080/greeting";
        }
        saveButton.click(function(e){
            formToSubmit.submit(function (e){
                var m_method = formToSubmit .attr('method');
                var m_action = formToSubmit .attr('action');
                var m_data  = formToSubmit.serialize();
                $.ajax({
                    type: m_method,
                    url: m_action,
                    data: m_data,
                    success: function(response){
                            window.location="http://localhost:8080/greeting";
                    }

                });

            });
        });
    });




});