
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
</div></div><div class=\"row categoryes\">";
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
    var html_start = "<div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><div class=\"section-title\">"+title+"</div></div></div><div class=\"row\"><div class=\"col-lg-12\">"
    var html_content = "";
    var html_end = "</div></div></div>"
    for (i=0; i<data.length; i++) {
      let desc = "["
      for (j=0; j<data[i].description.length; j+=2) {
        desc += data[i].description[j] + " - " + data[i].description[j+1] + ","
      }
      desc = desc.substr(0, desc.length - 1) + "]"
      html_content+="<div class=\"product\"><div class=\"row\"><div class=\"col-lg-3\"><div class=\"product-img\"><img src=\"images/"+data[i].image+"\"></div></div><div class=\"col-lg-7\"><div class=\"product-desc\"><div id=\""+data[i].id+"\" class=\"product-name\">"+data[i].name+"</div><div class=\"product-features\">"+desc+"</div></div></div><div class=\"col-lg-2\"><div class=\"buy-info\"><div class=\"product-price\">"+data[i].price+" р</div><div class=\"favorite\"><div class=\"buy-img-wrap\"><img src=\"images/favorite.png\"></div></div><div class=\"buy\"><div class=\"buy-img-wrap\"><img src=\"images/buy.png\"></div></div></div></div></div></div>"
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
            AddProductFav(arrayProdName[key])
          })
        });
         $(".buy").each(function(key){
          $(this).click(function(){
              //alert(arrayProdName[key]);
              AddProductBasket(arrayProdName[key])
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
    var html_start = "<div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><div class=\"section-title\">"+title+"</div></div></div><div class=\"row product-wrap\"><div class=\"col-lg-4\"><div class=\"prod-img\"><img src=\"images/"+data.image+"\"></div></div><div class=\"col-lg-4\"></div><div class=\"col-lg-4\"><div class=\"prod-buy\"><div class=\"price\">"+data.price+" р</div><div class=\"deliv\">Доставка: доступна</div><div class=\"garant\">Гарантия: 24 мес.</div><div class=\"to-basket\" onclick=AddProductBasket("+id+")>В корзину</div><div class=\"fav\" onclick=AddProductFav("+id+")>Избранное</div></div></div></div><div class=\"row\"><div class=\"col-lg-12\"><div class=\"characteristics-title\">Характеристики</div></div></div>"
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
    var html_start = "<div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><div class=\"section-title\">Результаты поиска</div></div></div><div class=\"row\"><div class=\"col-lg-12\">"
    var html_content = "";
    var html_end = "</div></div></div>"
    for (i=0; i<data.length; i++) {
      let desc = "["
      for (j=0; j<data[i].description.length; j+=2) {
        desc += data[i].description[j] + " - " + data[i].description[j+1] + ","
      }
      desc = desc.substr(0, desc.length - 1) + "]"
      html_content+="<div class=\"product\"><div class=\"row\"><div class=\"col-lg-3\"><div class=\"product-img\"><img src=\"images/"+data[i].image+"\"></div></div><div class=\"col-lg-7\"><div class=\"product-desc\"><div id=\""+data[i].id+"\" class=\"product-name\">"+data[i].name+"</div><div class=\"product-features\">"+desc+"</div></div></div><div class=\"col-lg-2\"><div class=\"buy-info\"><div class=\"product-price\">"+data[i].price+" р</div><div class=\"favorite\"><div class=\"buy-img-wrap\"><img src=\"images/favorite.png\"></div></div><div class=\"buy\"><div class=\"buy-img-wrap\"><img src=\"images/buy.png\"></div></div></div></div></div></div>"
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
              AddProductFav(arrayProdName[key])
          })
        });
         $(".buy").each(function(key){
          $(this).click(function(){
              //alert(arrayProdName[key]);
              AddProductBasket(arrayProdName[key])
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
