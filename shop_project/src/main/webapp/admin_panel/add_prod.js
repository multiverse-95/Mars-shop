
$(document).ready(function() {
    chooseCategory()
});

function chooseCategory(){
  $('#add_product_confirm').click(function() {
    var text1=$("#test_select :selected").text()
    alert(text1)
})
  
  
}

function add_prod() {
  $('.table_price tr:last').remove()
  $('.table_price tr:last').after('<tr><td><input class="add-text-name" placeholder="Введите имя..." type="text"></td><td><input class="add-text-value" placeholder="Введите значение..." type="text"></td><td><input class="del_prod" value="Удалить" type="button" onclick="$(this).closest(\'tr\').remove();"></td></tr> <tr><td></td><td></td><td><input id="add_tr_prod" class="add_prod" value="Добавить" type="button" onclick="add_prod()"></td></tr>');
 
}

