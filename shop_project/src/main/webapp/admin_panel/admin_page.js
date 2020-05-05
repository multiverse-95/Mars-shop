
$(document).ready(function() {
    toCategoryes();
    autoCompl();
    SearchProducts();
    authorizate_block();
});

function toCategoryes(){
  fetch('/shop_project/api/getCategoryes',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}})
    .then(function(response) { 
        if (response.ok) {
            return response.json();
        }
        else {
            console.log(response);
            throw response.text();
        }	       
    })
    .then(function(data) {
        var html_start = "<div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><div class=\"section-title\">Каталог товаров</div>\n\
\n\<br><div class=\"adding_prod\"><input id=\"add_product\" class=\"add_product-btn\" value=\"Добавить продукт\" type=\"button\" onclick=\"setAddProduct('', '')\"></div><br></div></div><div class=\"row categoryes\">";
        var html_content = "";
        var html_end = "</div></div>"
        for (i=0; i<data.length; i++) {
          html_content+="<div class=\"col-lg-3 col-md-4 col-6\"><div id=\""+data[i].id+"\" class=\"category\"><div class=\"category-img\"><img src=\"images/"+data[i].image+"\"></div><div class=\"category-title\">"+data[i].сategory_name+"</div></div></div>"
        }
        $('.main').html(html_start + html_content + html_end)
        $('.nav-links').html("MARS / Каталог")
        
        $('.category').click(function() {
            toCategory($(this).attr("id"), $(this).find(".category-title").html());
        })

    })
    .catch(function(error) {
        console.log(error);
        alert("Error catch: " + error);  
    });
}

function toCategory(id, title){
  var arrayProdName=[];
  var arrayProdTitle=[];
  fetch('/shop_project/api/getCategory',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: String(id)})
  .then(function(response) { 
    if (response.ok) {
      return response.json();
      }
        else {
      console.log(response);
        throw response.text();
      }	       
   })
  .then(function(data) {
    var html_start = "<div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><div class=\"section-title\">"+title+"</div>\n\
<br><div class=\"adding_prod\"><input id=\"add_product\" class=\"add_product-btn\" value=\"Добавить продукт\" type=\"button\" onclick=\"setAddProduct("+id+",\'"+title+"\')\"></div><br></div></div><div class=\"row\"><div class=\"col-lg-12\">";
    var html_content = "";
    var html_end = "</div></div></div>"
    for (i=0; i<data.length; i++) {
      let desc = "["
      for (j=0; j<data[i].description.length; j+=2) {
        desc += data[i].description[j] + " - " + data[i].description[j+1] + ","
      }
      desc = desc.substr(0, desc.length - 1) + "]"
      html_content+="<div class=\"product\"><div class=\"row\"><div class=\"col-lg-3\"><div class=\"product-img\"><img src=\"images/"+data[i].image+"\"></div></div><div class=\"col-lg-7\"><div class=\"product-desc\"><div id=\""+data[i].id+"\" class=\"product-name\">"+data[i].name+"</div><div class=\"product-features\">"+desc+"</div></div></div><div class=\"col-lg-2\"><div class=\"buy-info\"><div class=\"product-price\">"+data[i].price+" р</div><div class=\"favorite\"><div class=\"buy-img-wrap\"><img src=\"images/update.png\"></div></div><div class=\"buy\"><div class=\"buy-img-wrap\"><img src=\"images/delete.png\"></div></div></div></div></div></div>"
    }
    $('.main').html(html_start + html_content + html_end)

    $('.product-name').click(function() {
      toProduct($(this).attr("id"), $(this).html(), id, title);
      
    })
    $(".product-name").each(function(){
          arrayProdName.push($(this).attr("id"));
          arrayProdTitle.push($(this).html());
        });
        
        $(".product-img").each(function(key){
          $(this).click(function(){
              toProduct(arrayProdName[key], arrayProdTitle[key], id, title);
              
          })
        });
        
      $(".favorite").each(function(key){
          $(this).click(function(){
              //alert(arrayProdName[key])
              setParUpdateProduct(arrayProdName[key], arrayProdTitle[key], id, title);
          })
        });
         $(".buy").each(function(key){
          $(this).click(function(){
              //alert(arrayProdName[key])
              DeleteProduct(arrayProdName[key], arrayProdTitle[key], id, title);
              //setParUpdateProduct(arrayProdName[key], arrayProdTitle[key], id, title);
          })
        });
    
    
    $('.nav-links').html("MARS / <span onclick=\"toCategoryes()\" class=\"nav_link\">Каталог</span> / " + title)

    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
 
}

function toProduct(id, title, category_id, category_title){
  fetch('/shop_project/api/getProduct',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: String(id)})
  .then(function(response) { 
    if (response.ok) {
      return response.json();
      }
        else {
      console.log(response);
        throw response.text();
      }	       
   })
  .then(function(data) {
    var html_start = "<div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><div class=\"section-title\">"+title+"</div></div></div><div class=\"row product-wrap\"><div class=\"col-lg-4\"><div class=\"prod-img\"><img src=\"images/"+data.image+"\"></div></div><div class=\"col-lg-4\"></div><div class=\"col-lg-4\"><div class=\"prod-buy\"><div class=\"price\">"+data.price+" р</div><div class=\"deliv\">Доставка: доступна</div><div class=\"garant\">Гарантия: 24 мес.</div><div class=\"to-basket\">В корзину</div><div class=\"fav\">Избранное</div></div></div></div><div class=\"row\"><div class=\"col-lg-12\"><div class=\"characteristics-title\">Характеристики</div></div></div>"
    var html_content = "";
    var html_end = "</div>"
    for (i=0; i<data.description.length; i+=2) {
      html_content+="<div class=\"row characteristics\"><div class=\"col-lg-5\" style=\"padding-right: 5px;\"><div class=\"characteristic-name\"><div style=\"width: 200%\">"+data.description[i]+"................................................................................................</div></div></div><div class=\"col-lg-7\" style=\"padding-left: 5px;\"><div class=\"characteristic-value\">"+data.description[i+1]+"</div></div></div>"
    }
    $('.main').html(html_start + html_content + html_end)
    $('.nav-links').html("MARS / <span onclick=\"toCategoryes()\" class=\"nav_link\">Каталог</span> / <span onclick=\"toCategory("+category_id+",\'"+category_title+"\')\" class=\"nav_link\">"+category_title+"</span> / " + title)

    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
 
}

function setAddProduct(id, title){
    var html_content=`
<div class="container"><div class="row"><div class="col-lg-12"><div class="section-title">Добавление товара</div></div></div><div class="row"><div class="col-lg-12"></div>
          <br>
          <div class="_product-title"><h4>Название товара</h4></div>
          <div class="product_name">
            <input id="prod_name" class="add-text" placeholder="Введите название товара..." type="text">
          </div>

          <div class="_product-price"><h4>Цена товара</h4></div>
          <div class="product_price">
            <input id="prod_price" class="add-text" placeholder="Введите цену товара..." type="text">
          </div>

          <div class="_product-category"><h4>Категория товара</h4></div>
          <div class="product_category">
            <select  class="box" id="test_select" name="test_s">
              <option value="1">Плиты</option>
              <option value="2">Холодильники</option>
            </select>
          </div>

          <div class="_product-features"><h4></h4></div>
          <div class="product_features">
            <table class="table_price">
              <caption>Характеристики товара</caption>
              <tr>
                <th>Наименование</th>
                <th colspan="2">Значение</th>
              </tr>
              <tr>
                <td><input class="add-text-name" placeholder="Введите имя..." type="text"></td>
                <td><input class="add-text-value" placeholder="Введите значение..." type="text"></td>
                <td><input class="del_prod" value="Удалить" type="button" onclick="$(this).closest('tr').remove();"></td>
              </tr>
              <tr>
                <td><input class="add-text-name" placeholder="Введите имя..." type="text"></td>
                <td><input class="add-text-value" placeholder="Введите значение..." type="text"></td>
                <td><input class="del_prod" value="Удалить" type="button" onclick="$(this).closest('tr').remove();"></td>
              </tr>
              <tr>
                <td><input class="add-text-name" placeholder="Введите имя..." type="text"></td>
                <td><input class="add-text-value" placeholder="Введите значение..." type="text"></td>
                <td><input class="del_prod" value="Удалить" type="button" onclick="$(this).closest('tr').remove();"></td>
              </tr>
             
              <tr>
                <td></td>
                <td></td>
                <td><input id="add_tr_prod" class="add_prod" value="Добавить" type="button" onclick="add_prod_but()"></td>
              </tr>
              </table>
            
          </div>
          
          <br>
          <div class="_add_product">
            <input class="add-btn" value="Добавить товар" type="button" id="add_product_confirm" onclick="AddProduct()">
          </div>
`;
    $('.main').html(html_content)
    $('.nav-links').html("MARS / <span onclick=\"toCategoryes()\" class=\"nav_link\">Каталог</span> / <span onclick=\"toCategory("+id+",\'"+title+"\')\" class=\"nav_link\">"+category_title+"</span> / " + title)
    //AddProduct();
    
}

function AddProduct(){
    //$('#add_product_confirm').click(function() {
    //alert('hello')
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
           .then(function() { 
                  toCategory(category_id, category_product)
           })
           .catch(function(error) {
          console.log(error);
          alert("Error catch: " + error);  
          });
      } else {
        alert('Введите данные!')
      }
        
//});
}


function setParUpdateProduct(id, title, category_id, category_title){
    fetch('/shop_project/api/getProduct',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: String(id)})
  .then(function(response) { 
    if (response.ok) {
      return response.json();
      }
        else {
      console.log(response);
        throw response.text();
      }	       
   })
  .then(function(data) {
    var html_start=`<div class="container"><div class="row"><div class="col-lg-12"><div class="section-title">Изменение товара</div></div></div><div class="row"><div class="col-lg-12"></div>
          <br>
          <div class="_product-title"><h4>Название товара</h4></div>
          <div class="product_name">
            <input id="prod_name" class="add-text" placeholder="Введите название товара..." value="`+data.name+`" type="text">
          </div>

          <div class="_product-price"><h4>Цена товара</h4></div>
          <div class="product_price">
            <input id="prod_price" class="add-text" placeholder="Введите цену товара..." value="`+data.price+`"type="text">
          </div>

          <div class="_product-category"><h4>Категория товара</h4></div>
          <div class="product_category">
            <select  class="box" id="test_select" name="test_s">
              <option value="1">Плиты</option>
              <option value="2">Холодильники</option>
            </select>
          </div>

          <div class="_product-features"><h4></h4></div>
          <div class="product_features">
            <table class="table_price">
              <caption>Характеристики товара</caption>
              <tr>
                <th>Наименование</th>
                <th colspan="2">Значение</th>
              </tr>`;
    var html_content = "";
    var html_end = `
                <tr>
                <td></td>
                <td></td>
                <td><input id="add_tr_prod" class="add_prod" value="Добавить" type="button" onclick="add_prod_but()"></td>
              </tr>
              </table>
            
          </div>
          
          <br>
          <div class="_add_product">
            <input class="add-btn" value="Обновить товар" type="button" id="add_product_confirm">
          </div>`;
    for (i=0; i<data.feature_name.length; i++) {
      html_content+=`
                <tr>
                <td><input class="add-text-name" placeholder="Введите имя..." value="`+data.feature_name[i]+`" type="text"></td>
                <td><input class="add-text-value" placeholder="Введите значение..." value="`+data.feature_value[i]+`" type="text"></td>
                <td><input class="del_prod" value="Удалить" type="button" onclick="$(this).closest('tr').remove();"></td>
              </tr>`;
    }
    $('.main').html(html_start + html_content + html_end)
    $('.nav-links').html("MARS / <span onclick=\"toCategoryes()\" class=\"nav_link\">Каталог</span> / <span onclick=\"toCategory("+category_id+",\'"+category_title+"\')\" class=\"nav_link\">"+category_title+"</span> / " + title)
    $("#test_select :contains('"+category_title+"')").attr("selected", "selected");
    UpdateProduct(id,category_id, category_title);
    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
    
}

function UpdateProduct(id,category_id, category_title){
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
        fetch('/shop_project/api/UpdateProduct',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'},body: JSON.stringify({'id':id, 'name': name,'price': price, 'category_id':category_id,'image_id':image_id, 'feature_name':arrayProdFName, 'feature_value':arrayProdFValue})})
        .then(function(response) { 
            if (response.ok) {
              return response.json();
              }
                else {
              console.log(response);
                throw response.text();
              }	       
           })
           .then(function() { 
                  toCategory(category_id, category_product)
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

function add_prod_but() {
  $('.table_price tr:last').remove()
  $('.table_price tr:last').after('<tr><td><input class="add-text-name" placeholder="Введите имя..." type="text"></td><td><input class="add-text-value" placeholder="Введите значение..." type="text"></td><td><input class="del_prod" value="Удалить" type="button" onclick="$(this).closest(\'tr\').remove();"></td></tr> <tr><td></td><td></td><td><input id="add_tr_prod" class="add_prod" value="Добавить" type="button" onclick="add_prod_but()"></td></tr>');
 
}

function DeleteProduct(id, title, category_id, category_title){
      fetch('/shop_project/api/DeleteProduct',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'},body: JSON.stringify({'id':id})})
       .then(function(response) { 
            if (response.ok) {
              return response.json();
              }
                else {
              console.log(response);
                throw response.text();
              }	       
           })
       .then(function() { 
                  toCategory(category_id, category_title)
           })
            .catch(function(error) {
        console.log(error);
        alert("Error catch: " + error);  
        });
}

function autoCompl() {
$('#autocomplete-1').keyup(function(){
    findSearchAuto($(this).val());
 
 });
            
  }
  
  function findSearchAuto(search){
      fetch('/shop_project/api/goSearch',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: String(search)})
  .then(function(response) { 
    if (response.ok) {
      return response.json();
      }
        else {
      console.log(response);
        throw response.text();
      }	       
   })
  .then(function(data) {
    var searchProd  =  [];
    for (var i=0; i<data.length; i++){
        searchProd[i]=data[i].name;  
    }
            $( "#autocomplete-1" ).autocomplete({
                source: searchProd
            });
    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
      
  }
        

    
function SearchProducts(){
    $('#find_search').click(function() {
            GoSearchProducts($('.search-text').val());
        })       
    
}

function GoSearchProducts(search){
    var arrayProdName=[];
    var arrayProdTitle=[];
  fetch('/shop_project/api/goSearch',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: String(search)})
  .then(function(response) { 
    if (response.ok) {
      return response.json();
      }
        else {
      console.log(response);
        throw response.text();
      }	       
   })
  .then(function(data) {
    var category_id;
    var html_start = "<div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><div class=\"section-title\">Результаты поиска</div></div></div><div class=\"row\"><div class=\"col-lg-12\">"
    var html_content = "";
    var html_end = "</div></div></div>"
    for (i=0; i<data.length; i++) {
      let desc = "["
      for (j=0; j<data[i].description.length; j+=2) {
        desc += data[i].description[j] + " - " + data[i].description[j+1] + ","
      }
      desc = desc.substr(0, desc.length - 1) + "]"
      html_content+="<div class=\"product\"><div class=\"row\"><div class=\"col-lg-3\"><div class=\"product-img\"><img src=\"images/"+data[i].image+"\"></div></div><div class=\"col-lg-7\"><div class=\"product-desc\"><div id=\""+data[i].id+"\" class=\"product-name\">"+data[i].name+"</div><div class=\"product-features\">"+desc+"</div></div></div><div class=\"col-lg-2\"><div class=\"buy-info\"><div class=\"product-price\">"+data[i].price+" р</div><div class=\"favorite\"><div class=\"buy-img-wrap\"><img src=\"images/update.png\"></div></div><div class=\"buy\"><div class=\"buy-img-wrap\"><img src=\"images/delete.png\"></div></div></div></div></div></div>"
      category_id=data[i].category_id;
      }
    $('.main').html(html_start + html_content + html_end)

    $('.product-name').click(function() {
      toProduct($(this).attr("id"), $(this).html(), "", "");
    })
    
    $(".product-name").each(function(){
          arrayProdName.push($(this).attr("id"));
          arrayProdTitle.push($(this).html());
        });
        
        $(".product-img").each(function(key){
          $(this).click(function(){
              toProduct(arrayProdName[key], arrayProdTitle[key], '', '');
              
          })
        });
        
      $(".favorite").each(function(key){
          $(this).click(function(){
              //alert(arrayProdName[key])
              setParUpdateProduct(arrayProdName[key], arrayProdTitle[key], '', '');
          })
        });
         $(".buy").each(function(key){
          $(this).click(function(){
              //alert(arrayProdName[key])
              DeleteProduct(arrayProdName[key], arrayProdTitle[key], category_id, '');
              //setParUpdateProduct(arrayProdName[key], arrayProdTitle[key], id, title);
          })
        });
    $('.nav-links').html("MARS / <span onclick=\"toCategoryes()\" class=\"nav_link\">Каталог</span>  ")

    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
 
}




function del(user) {
  fetch('/Test/api/delTable',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'},body: JSON.stringify(user)})
  .then(test)
}
