$(document).ready(function() {
    $('.h-basket').click(function() {Productsbasket()})
    $('.h-favorite').click(function() {Productsfavorite()})
    $('.h-logout').click(async function(){
        user_data = await user_data_default;
        $('#user_name').html(user_data.login);
        $('.pacifier, .popup').unbind('mouseenter mouseleave');
        $('.popup').css('display', 'none');
        toCategoryes();
    });  
});

let user_data_default={
    avatar: "",
    city: "",
    country: "",
    first_name: "",
    flag: false,
    id: 0,
    imail: "",
    login: "Гость",
    mail_index: 000000,
    password: "",
    phone_number: 00000000000,
    second_name: "",
    street: "",
    third_name: ""
};
let user_data = user_data_default;
let orders = {};

const authorizate_block = function(){
    const onCl = function(){
        let login = $('#inp-login').val();
        let pass = $('#inp-pass').val();
        $('#Errors').css('display', 'none');
        
        if (login==="" || pass==="") {
            $('#Errors').html("<p>Не введены логин или пароль</p>");
            $('#Errors').css('display', 'flex');
        }
        else authorizate(login, pass);
    };
    const onCl_reg = function(){
        $('#Errors-reg').css('display', 'none');
        
        if ($('#inp-email-reg').val().match(/[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+/) === null || $('#inp-pass-reg').val() !== $('#inp-pass-reg-rep').val() || $('#inp-login-reg').val() === "" || $('#inp-pass-reg').val() === "") {
            let error_html = "";
            if ($('#inp-email-reg').val().match(/[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+/) === null) error_html += "<p>Не верно указан Email</p>";
            if ($('#inp-pass-reg').val() !== $('#inp-pass-reg-rep').val()) error_html += "<p>Пароли не совпадают</p>";
            if ($('#inp-login-reg').val() === "") error_html += "<p>Не введен логин</p>";
            if ($('#inp-pass-reg').val() === "") error_html += "<p>Не введен пароль</p>";
            $('#Errors-reg').html(error_html);
            $('#Errors-reg').css('display', 'flex');
        }
        else registration($('#inp-login-reg').val(), $('#inp-pass-reg').val(), $('#inp-email-reg').val());
    };
    $('.pacifier').click(function(){
        if (user_data.flag) ok();
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
    $("#reg-ref").click(function(){
        $(".login-form").css("display", "none");
        $(".reg-form").css("display", "block");
    });
    $('#SignUp').click(onCl_reg);
    $('.inputs-form-reg').keypress(function(event){if(event.keyCode === 13){onCl_reg();}});
};
const authorizate = async function(login, pass){
    await fetch('/shop_project/api/lk/authoriz', {
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
                    location.href='admin_panel/admin_page.html'
                } else {
                    $('.pacifier, .popup').hover(
                        function() {
                            $('.popup').css('visibility', 'visible').css('opacity', '1')
                        },
                        function() {
                            $('.popup').css('visibility', 'hidden').css('opacity', '0')
                        }
                    )
                    $('.pacifier').width(String($('.lk-onClk').width()) + 'px');
                    $('.popup').css('display', 'block');
                    $('.popup').css('visibility', 'hidden').css('opacity', '0');
                    
                    $(".login-form").css("display", "none");
                    $(".reg-form").css("display", "none");
                    $(".blockall").remove();
                    ok();
                }
            } else {
                $('#Errors').html("<p>Неверный логин или пароль</p>");
                $('#Errors').css('display', 'flex');
            }
            
        }
        else {
            //console.log(response);
            throw response.text();
        }
    });
};

const Productsbasket = function(){
    //alert(123)
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
          
              htmlBasketProd+=`<div class="lk-basket-description">
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
          htmlBasketProd=`<div style='margin-bottom: 10px; border-radius: 8px' class="basket-header">Корзина пуста</div>`;
      }
      
      var basket_html=`<div class="container basket-body">
                <div class="basket-header">Корзина пользователя</div>
                <div class="basket-block-body">
                    <div class="basket-list">
                        `+htmlBasketProd+`
                    </div>
                    <div style='width: 30%;'>
                        <div class="basket-summ">
                            <p><span>Сумма корзины: </span><span>`+sum_basket+` руб</span></p>
                            <input id="basketButton" type="button" value="Оформить заказ" onclick="DeleteAllProductBasket(`+sum_basket+`)"> 
                        </div>
                        <div id='tnx'></div>
                        <div class="popup-order">
                                    <p style='font-size: 17px; margin-top: 20px'> Подтвердить заказ? </p>
                                    <div style='display: flex; flex-direction: row; margin-bottom: 20px'>
                                        <input type='button' id='inp-yes' style='width: 40px; margin-right: 30px' value='Да'> 
                                        <input id='inp-no' style='width: 40px' type='button' value='Нет'> 
                                    </div>
                        </div>
                    </div>
                </div></div><br>
`;
      $('.main').html(basket_html);
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });  
}
const Productsfavorite = function(){
    fetch('/shop_project/api/GetProductsFavorite',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: String(user_data.id)})
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

    var htmlProd="";     
      for (var i=0; i<data.length; i++) {

        htmlProd+=`<div class="lk-fav-description" onclick="toProduct(`+data[i].id+`, '`+data[i].name+`', `+data[i].category_id+`, '')">
            <img src="images/`+data[i].image+`">
            <div class="fav-name">
                `+data[i].name+`
            </div>
            <div class="fav-order">
                <img id="del_name" src="images/delete.png" onclick="DeleteProductFav(`+data[i].id+`)">
                <div>
                    <div class="fav-price">`+data[i].price+` р</div>
                    <div class="fav-buy" onclick='AddProductBasket(event, `+ data[i].id +`)'>Купить</div>
                </div>
            </div>
        </div>`;
              
      }
      
      var fav_html=`<div class="container basket-body">
                <div class="basket-header">Желаемое</div>
                <div class="basket-block-body">
                    <div class="fav-list">
                        `+htmlProd+`
                    </div>
                </div></div><br>
`;
      $('.main').html(fav_html);

      if ($('.fav-list').find('div').is('div') == false) {
        $('.fav-list').html(`<div style='font-size: 20px; font-weight: bold; display: flex;justify-content: center;align-items: center;'>Нет желаемых товаров</div>`);
    }
    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });  
}

const ok = async function(){
    $('#user_name').html(user_data.login);
    $('.nav-links').html("<span onclick='toCategoryes()' class='nav_link'>MARS / </span><span>Личный Кабинет</span>");
    await $('.main').html("<div class='container lk-body'>"+
              "<div class='lk-block-data'>"+
                  "<div class='col-lg-12 lk-header'>Личные данные</div>"+
                  "<div class='col-lg-12 lk-block-body'>"+
                      "<div style='display: flex; flex-direction: row; margin: 15px 0 15px 0;'>"+
                            "<div style='width: 30%; display: flex; flex-direction: column; align-items: center; justify-content: center;'><img style='height: 100px; width: 150px;' src='"+ user_data.avatar +"'></div>"+
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
                                "<div id='email-er'></div>" +
                            "</div>"+
                            "<div style='width: 15%; display: flex; flex-direction: row'>"+
                                "<img onClick='upd_address()' id='pen_address' src='images/pen.png'><img id='save_address' onClick='upd_address_fetch()' src='images/save.png'>" +
                            "</div>"+
                      "</div>"+
                      "<div style='width: 100%; margin-right: 10px; margin-bottom:20px; display: flex; justify-content: center; align-items: center'><input style='font-size: 20px; megin-bottom:20px;' id='leave-lk' type='button' value='Выйти'></div>" +
                  "</div>                  "+
              "</div>"+
              "<div class='lk-block-orders'>"+
                  "<div class='col-lg-12 lk-header'>Заказы</div>"+
                  "<div class='lk-suborder-header'>"+
                      "<p>Текущие заказы</p>"+
                  "</div>"+
                  "<div class='lk-suborder-body' id='onView'>"+
                      "<div class='lk-order-description'>"+
                        "<p id='Nope'>Отсутсвуют</p>"+
                      "</div>"+
                  "</div>"+
                  "<div class='lk-suborder-header'>"+
                      "<p>Завершенные</p>"+
                  "</div>"+
                  "<div class='lk-suborder-body' id='onClose'>"+
                        "<p id='Nope'>Отсутсвуют</p>"+
                  "</div>"+   
              "</div>");
    $('#leave-lk').click(async function(){
        user_data = await user_data_default;
        $('#user_name').html(user_data.login);
        toCategoryes();
    });
    await orders_fetch();
    await get_orders();
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
    if ($('#im').val().match(/[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+/) == null) {
        $('#email-er').html('<p style="margin: 15px 0 0 15px">Неверно указана Почта</p>');
        $('#email-er').css('display', 'flex');
    }
    else {
        $('#email-er').css('display', 'none');
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
    //event.stopPropagation()
     if (user_data.flag==false){
         $(".login-form").css("display", "block");
         if($("div").hasClass("blockall")) $(".blockall").remove();
         else $(".tytoknoall").append('<div class="blockall"></div>');
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
const AddProductFav=async function (product_id){
     if (user_data.id==0){
        alert('Необходимо авторизоваться!')
     } else {
          await fetch('/shop_project/api/AddProductFav',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: JSON.stringify({'id':product_id, 'user_id':user_data.id})})
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
              alert('Добавлено в желаемое!');

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
      Productsbasket();
    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
};
const DeleteProductFav=async function (product_id){
     
    await fetch('/shop_project/api/DeleteProductFav',{method: 'POST', headers: {'Content-Type': 'application/json;charset=utf-8'}, body: JSON.stringify({'id':product_id, 'user_id':user_data.id})})
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
      Productsfavorite();
    
  })
  .catch(function(error) {
    console.log(error);
    alert("Error catch: " + error);  
    });
};

const DeleteAllProductBasket=function (sum_basket){
    if (sum_basket===0){
        $('#tnx').html("<p style='font-size: 25px; margin-top: 20px'>Корзина пуста!</p>");
        $('#tnx').css('display', 'flex');
        setTimeout(function(){$('#tnx').css('display', 'none')}, 2000);
    } else {
        if ($('.popup-order').css('display') === 'flex') $('.popup-order').css('display', 'none');
        else $('.popup-order').css('display', 'flex');
        $('#inp-yes').click(async function(){
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
              $('.popup-order').css('display', 'none');
              $('#tnx').html("<p style='font-size: 25px; margin-top: 20px'>Спасибо за покупку!</p>");
              $('#tnx').css('display', 'flex');
              setTimeout(function(){Productsbasket()}, 2000);  
            })
            .catch(function(error) {
              console.log(error);
              //alert("Error catch: " + error);  
              });
              
        });
        $('#inp-no').click(function(){
            $('.popup-order').css('display', 'none');
        })
        
    }
     
};

const registration = async function(login, pass, email){
    await fetch('/shop_project/api/lk/add-user', {
        method: 'POST', 
        headers: {'Content-Type': 'application/json;charset=utf-8'}, 
        body: JSON.stringify({
            login: login,
            password: pass,
            imail: email,
            role_number: 1
        })
     }).then(function(response) { 
        if (response)return response.json();
        else throw response.text();
     }).then(async function(data){
        if (data.login === "") {
            $('#Errors-reg').html("<p>Такой логин уже существует</p>");
            $('#Errors-reg').css('display', 'flex');
        }      
        else await authorizate($('#inp-login-reg').val(), $('#inp-pass-reg').val());
     });  
};

const get_orders = async function(){
    let orders_on = "";
    let orders_of = "";
    $('.lk-suborder-body:eq(0)').html("<p id='Nope'>Отсутсвуют</p>");
    $('.lk-suborder-body:eq(1)').html("<p id='Nope'>Отсутсвуют</p>");
    let ind = 0;
    orders.forEach((item, i) => {
        if (item.status === 0){
            orders_on += 
            "<div class='lk-order-description'>"+
                "<div style='width: 20%'>"+
                    "<img style='margin-right: 0' src='images/"+ item.image +"'>"+
                "</div>"+
                "<div style='width: 60%'>"+
                      "<p><span>"+ item.name +"</span></p>"+
                      "<p><span>x"+ item.count_products +"</span></p>"+
                      "<div style='display: flex; align-items: flex-end; width: 100%'><p><span>Сумма заказа: </span><span>"+ item.price +"руб</span></p></div>"+
                "</div>"+
                "<div style='width: 20%; margin-right: 10px; position: relative'>\n\
                    <input class='orderOk' onClick='order_ok("+ item.order_id + ", " + ind +")' type='button' value='Заказ получен'>"+
                    `<div class="popup-order-lk">
                        <p style='font-size: 13px; margin: 10px 0 10px 0; text-align: center'> Подтвердить? </p>
                        <div style='display: flex; flex-direction: row; margin-bottom: 10px'>
                            <input type='button' id='inp-yes-or' style='width: 40px; font-size: 13px; margin-right: 30px' value='Да'> 
                            <input id='inp-no-or' style='width: 40px; font-size: 13px' type='button' value='Нет'> 
                        </div>
                    </div>` +
                "</div>"+
            "</div>";
            ind++;
        } 
        else if (item.status === 1) {
            orders_of +=
            "<div class='lk-order-description'>"+
                "<div style='width: 20%'>"+
                    "<img style='margin-right: 0' src='images/"+ item.image +"'>"+
                "</div>"+
                "<div style='width: 60%'>"+
                      "<p><span>"+ item.name +"</span></p>"+
                      "<p><span>x"+ item.count_products +"</span></p>"+
                      "<div style='display: flex; align-items: flex-end; width: 100%'><p><span>Сумма заказа: </span><span>"+ item.price +"руб</span></p></div>"+
                "</div>"+
            "</div>";
        }                                               
    });
    if (orders_on !== "") await $('.lk-suborder-body:eq(0)').html(orders_on);
    if (orders_of !== "") await $('.lk-suborder-body:eq(1)').html(orders_of);
}
const orders_fetch = async function(){
    await fetch('/shop_project/api/lk/get-orders', {
            method: 'POST', 
            headers: {'Content-Type': 'application/json;charset=utf-8'}, 
            body: JSON.stringify({
                id: user_data.id
            })
        }).then(async function(response) { 
            if (response.ok) {
                return response.json();
            }
            else {
                //console.log(response);
                throw response.text();
            }	       
        }).then(async function(data) {
            orders = await data;
        });
}
const order_ok = async function(id, ind){
    if ($('.popup-order-lk:eq('+ ind +')').css('display') === 'flex') $('.popup-order-lk:eq('+ ind +')').css('display', 'none');
    else $('.popup-order-lk:eq('+ ind +')').css('display', 'flex');
    $('#inp-yes-or').click(async function(){
        $('.popup-order-lk:eq('+ ind +')').css('display', 'none');
        await fetch('/shop_project/api/lk/order-ok', {
            method: 'POST', 
            headers: {'Content-Type': 'application/json;charset=utf-8'}, 
            body: JSON.stringify({
                order_id: id
            })
        });
        await orders_fetch();
        await get_orders();
    }); 
    $('#inp-no-or').click(function(){
        $('.popup-order-lk:eq('+ ind +')').css('display', 'none');
    });
}