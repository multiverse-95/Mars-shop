let user_data={
    avatar: "",
    city: "",
    country: "",
    first_name: "",
    flag: false,
    id: 0,
    imail: "",
    login: "",
    mail_index: 000000,
    password: "",
    phone_number: 00000000000,
    second_name: "",
    street: "",
    third_name: ""
};

const authorizate_block = function(){
    const onCl = function(){
        let login = $('#inp-login').val();
        let pass = $('#inp-pass').val();
        
        if (login==="" || pass==="") alert("Не введены логин или пароль");
        else authorizate(login, pass);
    };
    $('.lk-onClk').click(function(){
        //if (user_data.flag) ok();
        if (user_data.flag) GetProductsbasket();
        else {
            $(".login-form").css("display", "block");
            if($("div").hasClass("blockall")) $(".blockall").remove();
            else $(".tytoknoall").append('<div class="blockall"></div>');
        }
    });
    $('.tytoknoall').click(function(){
        $(".login-form").css("display", "none");
        $(".reg-form").css("display", "none");
        $(".blockall").remove();
    });
    $('#SignIn').click(onCl);  
    $('.inputs-form').keypress(function(event){if(event.keyCode === 13){onCl();}});
};
const authorizate = async function(login, pass){
    $("#reg-ref").click(function(){
        $(".login-form").css("display", "none");
        $(".reg-form").css("display", "block");
    });
    await fetch('/shop_project/api/authoriz', {
        method: 'POST', 
        headers: {'Content-Type': 'application/json;charset=utf-8'}, 
        body: JSON.stringify({
            login: login,
            password: pass,
            id: 0,
            city: "",
            country: "",
            street: "",
            imail: "",
            first_name: "",
            second_name: "",
            third_name: "",
            mail_index: 000000,
            phone_number: 00000000000,
            avatar: "",
            flag: false
        })
    }).then(async function(response) { 
        if (response.ok) {
            user_data = await response.json();
            if(user_data.flag) {
                //await ok();
                if (user_data.role_number==2){
                    alert("Welcome admin!")
                    location.href='admin_panel/admin_page.html'
                } else {
                    await GetProductsbasket();
                    $(".login-form").css("display", "none");
                    $(".reg-form").css("display", "none");
                    $(".blockall").remove();
                }
            } else alert("Неверный логин или пароль");
        }
        else {
            //console.log(response);
            throw response.text();
        }	       
    });
};

const GetProductsbasket = function(){
    //alert(user_data.id)
    fetch('/shop_project/api/GetProductsBasket',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: String(user_data.id)})
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
      var ManyProductsAll=[];
      var htmlBasketAll="";
      var htmlBasketProd="";
      var htmlBasketMany="";
      var sum_basket=0;
      var sum_count=0;
      
      
      for (var i=0; i<data.length; i++) {
          
          sum_count=data[i].count_products;
          sum_basket+=data[i].price;
          //alert('sum_count: '+ sum_count)
          
              htmlBasketProd+=`<div class="lk-order-description">
                            <img src="images/`+data[i].image+`">
                            <div style="width: 95%;" onclick="toProduct(`+data[i].id+`, '`+data[i].name+`', `+data[i].category_id+`, '')">
                                <div>
                                  <p><span>`+data[i].name+`</span></p>
                                  <p><span>Количество: x`+sum_count+`</span></p>
                                  <div style="display: flex; align-items: flex-end; width: 100%"><p><span>Сумма: </span><span>`+data[i].price+` руб</span></p></div>
                                </div>
                            </div>
                            <div style="width: 5%;"><img id="del_name" src="images/delete.png" onclick="DeleteProductBasket(`+data[i].id+`)"></div>
                        </div>`;
              
      }
     
      if (sum_basket==0){
          htmlBasketProd=`<div class="basket-header">Товаров в корзине нет!</div>`;
      }
      
      var basket_html=`<div class="container basket-body">
                <div class="basket-header">Корзина пользователя</div>
                <div class="basket-block-body">
                    <div class="basket-list">
                        `+htmlBasketProd+`
                    </div>
                    <div class="basket-summ">
                        <p><span>Сумма корзины: </span><span>`+sum_basket+` руб</span></p>
                        <input id="basketButton" type="button" value="Оформить заказ" onclick="DeleteAllProductBasket(`+sum_basket+`)"> 
                    </div>
                </div></div><br>
`;
      ok(basket_html);
    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
    
    
}


const ok = function(basket_html){
    $('#user_name').html(user_data.login);
    $('.nav-links').html("<span onclick='toCategoryes()' class='nav_link'>Марс / </span><span>Личный Кабинет</span>");
    $('.main').html(basket_html+"<div class='container lk-body'>"+
              "<div class='lk-block-data'>"+
                  "<div class='col-lg-12 lk-header'>Личные данные</div>"+
                  "<div class='col-lg-12 lk-block-body'>"+
                      "<div style='display: flex; flex-direction: row; margin: 15px 0 15px 0;'>"+
                            "<div style='width: 30%; display: flex; flex-direction: column; align-items: center; justify-content: center;'><img style='height: 100px; width: 150px;' src='"+ user_data.avatar +"'><p style='font-size: 15px; margin: 2px 0 0 0; text-align: center;'>Изменить</p><p style='font-size: 15px; margin: 2px 0 0 0; text-align: center;'>аватар</p></div>"+
                            "<div style='margin-left: 15px; display: flex; flex-direction: column; justify-content: center; width: 55%'>"+
                              "<p><span>Ф: </span><span id='s_name'>"+ user_data.second_name +"</span></p>"+
                              "<p><span>И: </span><span id='f_name'>"+ user_data.first_name +"</span></p>"+
                              "<p><span>О: </span><span id='t_name'>"+ user_data.third_name +"</span></p>"+
                            "</div>"+
                            "<div style='width: 15%; display: flex; flex-direction: row'>"+
                              "<img id='pen_name' onClick='upd_name()' src='images/pen.png'><img id='save_name' onClick='upd_name_fetch()' src='images/save.png'>" +
                            "</div>"+
                      "</div>"+
                      "<div style='border-top: 1px solid lightgrey; padding: 15px 0 15px 15px; display: flex; flex-direction: row'>"+            
                            "<div style='width: 85%;'>"+
                                "<p style='display: flex; flex-direction: row'><span style='margin-right: 5px'>Страна: </span><span id='co'>"+ user_data.country +"</span></p>"+
                                "<p><span>Город: </span><span id='ci'>"+ user_data.city +"</span></p>"+
                                "<p><span>Улица, дом: </span><span id='st'>"+ user_data.street +"</span></p>"+
                                "<p><span>Почтовый индекс: </span><span id='m_i'>"+ user_data.mail_index +"</span></p>"+
                                "<p><span>Телефон: </span><span id='p_n'>"+ user_data.phone_number +"</span></p>"+
                                "<p><span>Почта: </span><span id='im'>"+ user_data.imail +"</span></p>"+
                            "</div>"+
                            "<div style='width: 15%; display: flex; flex-direction: row'>"+
                                "<img onClick='upd_address()' id='pen_address' src='images/pen.png'><img id='save_address' onClick='upd_address_fetch()' src='images/save.png'>" +
                            "</div>"+
                      "</div>"+
                  "</div>                  "+
              "</div>"+
              "<div class='lk-block-orders'>"+
                  "<div class='col-lg-12 lk-header'>Заказы</div>"+
                  "<div class='lk-suborder-header'>"+
                      "<p>На рассмотрении</p>"+
                  "</div>"+
                  "<div class='lk-suborder-body' id='onView'>"+
                      "<div class='lk-order-description'>"+
                          "<div><img src='images/refrigerator.png'></div>"+
                          "<div style='margin: 0 0 0 20px;'>"+
                              "<div>"+
                                "<p><span>Холодос Mdawdaw2321</span></p>"+
                                "<p><span>x1</span></p>"+
                                "<p><span>Дата заказа: </span><span>12.31.2019</span></p>"+
                                "<div style='display: flex; align-items: flex-end; width: 100%'><p><span>Сумма заказа: </span><span>23222руб</span></p></div>"+
                              "</div>"+
                          "</div>"+
                      "</div>"+
                      "<div class='lk-order-description'>"+
                          "<div>"+
                              "<img src='images/refrigerator.png'>"+
                          "</div>"+
                          "<div style='margin: 0 0 0 20px;'>"+
                              "<div>"+
                                "<p><span>Холодос Mdawdaw2321</span></p>"+
                                "<p><span>x1</span></p>"+
                                "<p><span>Дата заказа: </span><span>12.31.2019</span></p>"+
                                "<div style='display: flex; align-items: flex-end; width: 100%'><p><span>Сумма заказа: </span><span>23222руб</span></p></div>"+
                              "</div>"+
                          "</div>"+
                      "</div>"+
                  "</div>"+
                  "<div class='lk-suborder-header'>"+
                      "<p>В пути</p>"+
                  "</div>"+
                  "<div class='lk-suborder-body' id='onDelivery'>"+
                      "<p id='Nope'>Отсутствуют</p>"+
                  "</div>"+
                  "<div class='lk-suborder-header'>"+
                      "<p>Завершенные</p>"+
                  "</div>"+
                  "<div class='lk-suborder-body' id='onClose'>"+
                      "<div class='lk-order-description'>"+
                          "<div>"+
                              "<img src='images/refrigerator.png'>"+
                          "</div>"+
                          "<div style='margin: 0 0 0 20px;'>"+
                              "<div>"+
                                "<p><span>Холодос Mdawdaw2321</span></p>"+
                                "<p><span>x1</span></p>"+
                                "<p><span>Дата заказа: </span><span>12.31.2019</span></p>"+
                                "<div style='display: flex; align-items: flex-end; width: 100%' ><p><span>Сумма заказа: </span><span>23222руб</span></p></div>"+
                              "</div>"+
                          "</div>"+
                      "</div>"+
                  "</div>"+   
              "</div>");
};

const upd_name = function(){
    $('#f_name').replaceWith(function(){return "<input style='padding-left: 5px; width: 120px; height: 25px' id='f_name' value='"+ user_data.first_name +"' >";});
    $('#s_name').replaceWith(function(){return "<input style='padding-left: 5px; width: 120px; height: 25px' id='s_name' value='"+ user_data.second_name +"' >";});
    $('#t_name').replaceWith(function(){return "<input style='padding-left: 5px; width: 120px; height: 25px' id='t_name' value='"+ user_data.third_name +"' >";});
    $('#save_name').css("display", "block");
};
const upd_name_fetch = async function(){
    if (user_data.first_name !== $('#f_name').val() || user_data.second_name !== $('#s_name').val() || user_data.third_name !== $('#t_name').val()){
       await fetch('/shop_project/api/lk/upd-name', {
            method: 'POST', 
            headers: {'Content-Type': 'application/json;charset=utf-8'}, 
            body: JSON.stringify({
                id: user_data.id,
                first_name: $('#f_name').val(),
                second_name: $('#s_name').val(),
                third_name: $('#t_name').val()
            })
        }).then(function(response) { 
            if (response)return response.json();
            else throw response.text();
        }).then(async function(data){
            user_data.first_name = await data.first_name;
            user_data.second_name = await data.second_name;
            user_data.third_name = await data.third_name;
        });  
    }
    $('#f_name').replaceWith(function(){return "<span id='f_name'>"+ user_data.first_name +"</span>";});
    $('#s_name').replaceWith(function(){return "<span id='s_name'>"+ user_data.second_name +"</span>";});
    $('#t_name').replaceWith(function(){return "<span id='t_name'>"+ user_data.third_name +"</span>";});
    $('#save_name').css("display", "none");
};

const upd_address = function(){
    $('#co').replaceWith(function(){return "<input style='padding-left: 5px; width: 150px; height: 25px' id='co' value='"+ user_data.country +"' >";});
    $('#ci').replaceWith(function(){return "<input style='padding-left: 5px; width: 150px; height: 25px' id='ci' value='"+ user_data.city +"' >";});
    $('#st').replaceWith(function(){return "<input style='padding-left: 5px; width: 150px; height: 25px' id='st' value='"+ user_data.street+"' >";});
    $('#m_i').replaceWith(function(){return "<input style='padding-left: 5px; width: 150px; height: 25px' id='m_i' value='"+ user_data.mail_index +"' >";});
    $('#p_n').replaceWith(function(){return "<input style='padding-left: 5px; width: 150px; height: 25px' id='p_n' value='"+ user_data.phone_number +"' >";});
    $('#im').replaceWith(function(){return "<input style='padding-left: 5px; width: 150px; height: 25px' id='im' value='"+ user_data.imail +"' >";});
    $('#save_address').css("display", "block");
};
const upd_address_fetch = async function(){
    console.log($('#co').val(), $('#ci').val(), $('#st').val(), $('#m_i').val(), $('#p_n').val(), $('#im').val());
    if ($('#im').val().match(/[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+/) == null) alert('Не верно указана почта');
    else {
        if (user_data.country != $('#co').val() || user_data.city != $('#ci').val() || user_data.street != $('#st').val() || user_data.mail_index != $('#m_i').val() || user_data.phone_number != $('#p_n').val() || user_data.imail != $('#im').val()){
           await fetch('/shop_project/api/lk/upd-address', {
                method: 'POST', 
                headers: {'Content-Type': 'application/json;charset=utf-8'}, 
                body: JSON.stringify({
                    id: user_data.id,
                    country: $('#co').val(),
                    city: $('#ci').val(),
                    street: $('#st').val(),
                    mail_index: $('#m_i').val(),
                    phone_number: $('#p_n').val(),
                    imail: $('#im').val()
                })
            }).then(function(response) { 
                if (response)return response.json();
                else throw response.text();
            }).then(async function(data){
                user_data.country = await data.country;
                user_data.city = await data.city;
                user_data.street = await data.street;
                user_data.mail_index = await data.mail_index;
                user_data.phone_number = await data.phone_number;
                user_data.imail = await data.imail;      
            });  
        }
        $('#co').replaceWith(function(){return "<span id='co'>"+ user_data.country +"</span>";});
        $('#ci').replaceWith(function(){return "<span id='ci'>"+ user_data.city +"</span>";});
        $('#st').replaceWith(function(){return "<span id='st'>"+ user_data.street +"</span>";});
        $('#m_i').replaceWith(function(){return "<span id='m_i'>"+ user_data.mail_index +"</span>";});
        $('#p_n').replaceWith(function(){return "<span id='p_n'>"+ user_data.phone_number +"</span>";});
        $('#im').replaceWith(function(){return "<span id='im'>"+ user_data.imail +"</span>";});
        $('#save_address').css("display", "none");
    }
    
};

const AddProductBasket=async function (product_id){
     //alert('Success!'+product_id)
     if (user_data.id==0){
         alert('Необходимо авторизоваться!')
     } else {
          await fetch('/shop_project/api/AddProductBasket',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: JSON.stringify({'id':product_id, 'user_id':user_data.id})})
            .then(async function(response) { 
              if (response.ok) {
                return response.json();
                }
                  else {
                console.log(response);
                  throw response.text();
                }	       
             })
            .then(async function() {
              alert('Добавлено в корзину!');

            })
            .catch(function(error) {
              console.log(error);
              alert("Error catch: " + error);  
              });
     }
   
};

const DeleteProductBasket=async function (product_id){
     
    await fetch('/shop_project/api/DeleteProductBasket',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: JSON.stringify({'id':product_id, 'user_id':user_data.id})})
  .then(async function(response) { 
    if (response.ok) {
      return response.json();
      }
        else {
      console.log(response);
        throw response.text();
      }	       
   })
  .then(async function() {
    //alert('Добавлено в корзину!');
      GetProductsbasket();
    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
};

const DeleteAllProductBasket=async function (sum_basket){
    if (sum_basket==0){
        alert('В корзине нет товаров!')
    } else {
        await fetch('/shop_project/api/DeleteAllProductBasket',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: JSON.stringify({'user_id':user_data.id})})
  .then(async function(response) { 
    if (response.ok) {
      return response.json();
      }
        else {
      console.log(response);
        throw response.text();
      }	       
   })
  .then(async function() {
    alert('Спасибо за покупку!');
      GetProductsbasket();
    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
    }
     
};
