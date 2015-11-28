
$(document).ready(function() {
    var tag_counter         = 0;
    var max_fields          = 10; //maximum input boxes allowed
    var wrapper             = $("#ingredients"); //Fields wrapper
    var add_button          = $("#add_ingredient_button"); //Add button ID


    var before_search = "http://api.nal.usda.gov/ndb/search/?format=json&q=";
    var after_search = "&sort=r&max=10&offset=0&api_key=AwzIs7zMikmJmys8pXirum9MUm4SKv3pf384o8tX";

    var food_buffer=[];




    var ingredient_buffer_en=[];
    var ingredient_buffer_carb=[];
    var ingredient_buffer_prot=[];
    var ingredient_buffer_fat=[];

    var ingredient_buffer_name=[];
    var ingredient_buffer_ndb_num=[];


    var ingredient_name     = ["ingredient_name[0]","ingredient_name[1]","ingredient_name[2]","ingredient_name[3]","ingredient_name[4]","ingredient_name[5]","ingredient_name[6]","ingredient_name[7]","ingredient_name[8]","ingredient_name[9]"];
    var ingredient_amount   = ["ingredient_amount[0]","ingredient_amount[1]","ingredient_amount[2]","ingredient_amount[3]","ingredient_amount[4]","ingredient_amount[5]","ingredient_amount[6]","ingredient_amount[7]","ingredient_amount[8]","ingredient_amount[9]"];
    var ingredient_no       = ["ingredient_no[0]","ingredient_no[1]","ingredient_no[2]","ingredient_no[3]","ingredient_no[4]","ingredient_no[5]","ingredient_no[6]","ingredient_no[7]","ingredient_no[8]","ingredient_no[9]"];

    var arrayForTags        = ["tag[0]","tag[1]","tag[2]","tag[3]","tag[4]","tag[5]","tag[6]","tag[7]","tag[8]","tag[9]"];



    $("#add_tag").on("click",function(){
        if(tag_counter<max_fields){
            tag_counter++;
            var tag_template = $("#tag_template").clone();

            tag_template.removeAttr("id");
            tag_template.addClass("tag_instance");
            tag_template.removeAttr("style");

            //tag_template.find("input").attr("name" , arrayForTags[tag_counter-1]);
            tag_template.find("input").attr("name" , "tag");

            $("#tags").append(tag_template);
        }
    });

    $("#remove_tag").on("click",function(){
        if(tag_counter>0){
            tag_counter--;
            $("#tags").children().last().remove();
        }
    });

    $("#look_for").on("click",function() {
        $("#look_for_panel_body").slideToggle( "slow" );
    });

    $(document.body).on("click", ".add_button" , function () {

        if(ingredient_buffer_name.length < max_fields) {

            var btn = $(this);
            var ndbnum = btn.data("ndb-num");
            var foodname = btn.data("food-name");

            var apicall = "http://api.nal.usda.gov/ndb/reports/?ndbno="+ndbnum+"&type=b&format=json&api_key=AwzIs7zMikmJmys8pXirum9MUm4SKv3pf384o8tX";
            $.getJSON(apicall,function(data){
                nutrients = data["report"]['food']['nutrients'];

                var kcal;
                var carb;
                var prot;
                var fat;

                for(var i = 0; i< nutrients.length ; i++){
                    if(nutrients[i].name == "Energy" && nutrients[i].unit == "kcal" ){
                        kcal = nutrients[i].value;
                    }
                    if(nutrients[i].name == "Protein"){
                        prot = nutrients[i].value;
                    }
                    if(nutrients[i].name == "Total lipid (fat)"){
                        fat = nutrients[i].value;
                    }
                    if(nutrients[i].name == "Carbohydrate, by difference"){
                        carb= nutrients[i].value;
                    }
                }

                ingredient_buffer_en.push(kcal);
                ingredient_buffer_carb.push(carb);
                ingredient_buffer_prot.push(prot);
                ingredient_buffer_fat.push(fat);

                ingredient_buffer_name.push(foodname);
                ingredient_buffer_ndb_num.push(ndbnum);

                displayIngredientBuffer();


            });



        }
    });



    $(document.body).on("click", "#rm_button" , function () {
        var index  = $(this).index();
        ingredient_buffer_name.splice(index,1);
        ingredient_buffer_ndb_num.splice(index,1);

        ingredient_buffer_en.splice(index,1);
        ingredient_buffer_carb.splice(index,1);
        ingredient_buffer_prot.splice(index,1);
        ingredient_buffer_fat.splice(index,1);


        displayIngredientBuffer();

    });

    $("#search").on("click",function(){
        var query = $("#query").val();
        lookfor(query);
    });








    function lookfor(q){
        var fullurl = before_search + q + after_search;
        $.getJSON(fullurl,function(data){
            food_buffer = data["list"]['item'];
            displayFoodBuffer();
        });
    }

    function displayFoodBuffer() {
        $(".table_row_instance").remove();
//            var table_row = $('#table_row_template').clone();
        for (i = 0; i < food_buffer.length; i++) {
            var table_row = $('#table_row_template').clone();
            table_row.children().first().text(food_buffer[i].name);
            table_row.children().find(".add_button").attr("data-ndb-num", food_buffer[i].ndbno);
            table_row.children().find(".add_button").attr("data-food-name", food_buffer[i].name);

            table_row.removeAttr("class", "table_row_template");
            table_row.attr("class", "table_row_instance");

            $(".table").append(table_row);
        }
    }

    function displayIngredientBuffer() {
        $(wrapper).children().remove();
        for (var i = 0; i < ingredient_buffer_name.length; i++) {
            var foodname = ingredient_buffer_name[i];
            var ndbnum = ingredient_buffer_ndb_num[i];
            var en = ingredient_buffer_en[i];
            var carb = ingredient_buffer_carb[i];
            var prot = ingredient_buffer_prot[i];
            var fat = ingredient_buffer_fat[i];
            var toberinserted = generateIngredientHtml(i,ndbnum,foodname,en,carb,prot,fat);

            $(wrapper).append(toberinserted);
        }
    }







    function generateIngredientHtml(num,ndbnum,foodname,en,carb,prot,fat) {

        var template = $("#ingredient_template").clone();

        var label   = "Ingredient";

        template.find(".control-label").text(label);


        //template.find("#name").attr("name" , ingredient_name[num]);
        template.find("#name").attr("name" , "ingredient_name");
        template.find("#name").attr("value" , foodname);

        //template.find("#amount").attr("name" , ingredient_amount[num]);
        template.find("#amount").attr("name" , "ingredient_amount");

        //template.find("#ndb_no").attr("name" , ingredient_no[num]);
        template.find("#ndb_no").attr("name" , "ingredient_no");

        template.find("#ndb_no").attr("value" , ndbnum);

        template.find("#en").attr("value" , en);
        template.find("#carb").attr("value" , carb);
        template.find("#prot").attr("value" , prot);
        template.find("#fat").attr("value" , fat);



        template.removeAttr('style');
        template.attr("id" , "ingredient");

        return template;

    }









});

