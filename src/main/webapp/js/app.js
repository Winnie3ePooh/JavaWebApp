function getContacts() {
    $.getJSON('contacts?action=list', function(data) {
        $.each(data, function(key, val) {
            var tr = $('<tr></tr>');
            $.each(val, function(k, v) {
                $('<td>' + v + '</td>').appendTo(tr);
            });
            tr.appendTo('#response');
        });
    });
}

function removeBtn(obj, id) {
    if (confirm("Удалить контакт?")) {
        var parent = $(obj).parent().parent();
        $.getJSON('contacts?action=remove&id=' + id, function(data) {
            parent.fadeOut('slow', function() {
                $(this).remove();
            });
        });
    }
}

function addBtn() {
    $('form').submit(function() {
        var arr = $(this).serializeArray();
        $.getJSON('contacts?action=add&mesText=' + arr[0].value
        );
        return false;
    });
}
