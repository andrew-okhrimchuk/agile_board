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
                    window.location="http://localhost:80/agile_board/greeting";
                }

            }

        });
    });

    $('.deleteButton').click(function (e){
            var formToSubmit =  $(this).parent().parent().children(":first");
            formToSubmit.attr('action', "/agile_board/deleteticket");
            formToSubmit.find('input[name = "name"]').removeAttr('disabled');
            formToSubmit.submit();
    });

    $('.editButton').click(function(e){
        clickCount ++;
        event.preventDefault();
        var formToSubmit =  $(this).parent().parent().children(":first");
        var saveButton =  formToSubmit.find('input[value = "Save"]').show();
        var description = formToSubmit.find('textarea');
        formToSubmit.attr('action', "/agile_board/editticket");
        formToSubmit.find('input[name = "name"]').removeAttr('disabled');
        description.removeAttr('disabled');
        formToSubmit.find('input[value = "Delete"]').attr('disabled',"disabled");
        formToSubmit.find('input[value = "Move to"]').attr('disabled',"disabled");
        if( clickCount == 2) {
            window.location = "http://localhost:80/agile_board/greeting";
        }
        saveButton.click(function(e){
            formToSubmit.submit();
        });
    });

    $('.moveToButton').click(function(){
        var menu = $(this).parent().parent().children(":nth-child(3)");
        clickCount ++;
        if(clickCount == 2 || clickCount  == 0){
            menu.hide();
            clickCount = 0;
        }else{
            menu.show();
        }
        $('.menuButtons').click(function(){
           var submitForm = $(this).parent().parent().parent().parent().children(":first");
           var newColumn =  $(this).val();
           submitForm.attr('action', "/agile_board/moveticket");
            submitForm.find('input[name = "newColumn"]').attr('value', newColumn);
           submitForm.find('input[name = "name"]').removeAttr('disabled');
            submitForm.find('textarea').removeAttr('disabled');
           submitForm.submit();
        });

    });




});