
$(document).ready(function() {
    chooseCategory()
    //name_val()
});

function name_val(){
    $('.add-text-name').click(function() {
      var namepr=$(this).val()
      alert(namepr)
      });
}

function chooseCategory(){
  $('#add_product_confirm').click(function() {
    var category_product=$("#test_select :selected").text()
    var category_id=0;
    var image_id=0;
    if(category_product=='Плиты'){
        category_id=1;
        image_id=4;
    } else if (category_product =='Холодильники'){
        category_id=2;
        image_id=5;
    }
     
      var name=$('#prod_name').val()
      var price=$('#prod_price').val()
      var arrayProdFName = [];
      var arrayProdFValue = [];
        $(".add-text-name").each(function(){
          arrayProdFName.push($(this).val());
        });
        $(".add-text-value").each(function(){
          arrayProdFValue.push($(this).val());
        });
        
      
      if (name!="" && price!=""){
        fetch('/shop_project/api/addProduct',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'},body: JSON.stringify({'name': name,'price': price, 'category_id':category_id,'image_id':image_id, 'feature_name':arrayProdFName, 'feature_value':arrayProdFValue})})
        .then(function(response) { 
            if (response.ok) {
              return response.json();
              }
                else {
              console.log(response);
                throw response.text();
              }	       
           })
           .catch(function(error) {
          console.log(error);
          alert("Error catch: " + error);  
          });
      } else {
        alert('Введите данные!')
      }
        
});
  
  
}

function add_prod() {
  $('.table_price tr:last').remove()
  $('.table_price tr:last').after('<tr><td><input class="add-text-name" placeholder="Введите имя..." type="text"></td><td><input class="add-text-value" placeholder="Введите значение..." type="text"></td><td><input class="del_prod" value="Удалить" type="button" onclick="$(this).closest(\'tr\').remove();"></td></tr> <tr><td></td><td></td><td><input id="add_tr_prod" class="add_prod" value="Добавить" type="button" onclick="add_prod()"></td></tr>');
 
}

