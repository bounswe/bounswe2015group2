
$(document).ready(function() {
    var max_fields          = 10;

    var wrapper_searched_ingredients    = $("#searchedingredients");
    var wrapper_added_ingredients       = $("#addedingredients");
    var wrapper_added_tags              = $("#tags");

    var template_tag                    = $("#tag_template");
    var template_searched_ingredient    = $("#searched_ingredient_template");
    var template_added_ingredient       = $("#added_ingredient_template");

    var search_call_p1  = "http://api.nal.usda.gov/ndb/search/?format=json&q=";
    var search_call_p2  = "&sort=r&max=10&offset=0&api_key=AwzIs7zMikmJmys8pXirum9MUm4SKv3pf384o8tX";

    var find_call_p1    = "http://api.nal.usda.gov/ndb/reports/?ndbno=";
    var find_call_p2    = "&type=f&format=json&api_key=AwzIs7zMikmJmys8pXirum9MUm4SKv3pf384o8tX";

    var added_ingredient_values=[];

    //// LOOK FOR ANIMATION
    $("#look_for_panel_body").slideToggle( "slow" );
    $("#look_for").on("click",function() {
        $("#look_for_panel_body").slideToggle( "slow" );
    });

    //// SEARCH BUTTON LISTENER ON SEARCHED INGREDIENTS
    $("#search").on("click",function(){
        var query = $("#query").val();
        var search_call = search_call_p1+ query + search_call_p2;
        $.getJSON(search_call,function(data){
            wrapper_searched_ingredients.children().remove();
            var searched_ingredients = data["list"]['item'];
            searched_ingredients.forEach(function(ingredient){
                var ingredient_to_be_added = generateSearchedIngredientHTML(ingredient.ndbno,ingredient.name);
                wrapper_searched_ingredients.append(ingredient_to_be_added);
            });



        });
    });

    //// ADD BUTTON LISTENER ON SEARCHED INGREDIENTS
    $(document.body).on("click", ".add_button" , function () {
        var added_ingredient_count = wrapper_added_ingredients.children().length;
        if(added_ingredient_count < max_fields) {
            var btn = $(this);

            var ndbno   = btn.data("ingredient-ndbno");
            var name    = btn.data("ingredient-name");
            var find_ingredient_call = find_call_p1 + ndbno + find_call_p2;

            $.getJSON(find_ingredient_call,function(data){
                var nutrients = data["report"]['food'];
                added_ingredient_values.push(nutrients);
                var ingredient_to_be_added = generateAddedIngredientHTML(ndbno,name,nutrients.fg);
                wrapper_added_ingredients.append(ingredient_to_be_added);
                setValues(ndbno,0);
            });

        }
    });

    //// REMOVE BUTTON LISTENER ON ADDED INGREDIENTS
    $(document.body).on("click", ".rm_button" , function () {
        var index  = $(this).parent().parent().index();
        //var ndb = wrapper_added_ingredients.find("#ndbno");
        var num = $(wrapper_added_ingredients[index]).find("#ndbno").attr("value");
        removeValues(num);
        wrapper_added_ingredients.children()[index].remove();
    });

    //// TAG BUTTON LISTENERS
    $("#add_tag").on("click",function(){
        var tag_count = wrapper_added_tags.children().length;
        if(tag_count<max_fields){
            var candidate_tag = template_tag.clone();

            candidate_tag.removeAttr("id");
            candidate_tag.addClass("tag_instance");
            candidate_tag.removeAttr("style");
            //candidate_tag.find("input").attr("name" , "tag");

            $("#tags").append(candidate_tag);
        }
    });
    $("#remove_tag").on("click",function(){
        var tag_count = wrapper_added_tags.children().length;
        if(tag_count>0){
            $("#tags").children().last().remove();
        }
    });

    $('#add_recipe_form').submit(function() {
        var ingredient_count = wrapper_added_ingredients.children().length;
        var tag_count = wrapper_added_tags.children().length;
        if(ingredient_count == 0 || tag_count == 0){
            bootbox.alert("Tag list or Ingredient list cannot be empty!");
            return false;
        }
        return true;
    });

    function generateSearchedIngredientHTML(ndbno,name){
        var searched_ingrendient = template_searched_ingredient.clone();

        searched_ingrendient.children().first().text(name);
        searched_ingrendient.children().find(".add_button").attr("data-ingredient-ndbno", ndbno);
        searched_ingrendient.children().find(".add_button").attr("data-ingredient-name", name);

        searched_ingrendient.removeAttr("class", "table_row_template");
        searched_ingrendient.attr("class", "table_row_instance");

        return searched_ingrendient;
    }
    function generateAddedIngredientHTML(ndbno,name,group) {

        var template = template_added_ingredient.clone();

        template.find(".control-label").text("Ingredient");

        template.find("#ndbno").attr("value" , ndbno);
        template.find("#unit").attr("data-ndb" , ndbno); // ?
        template.find("#name").attr("value" , name);
        template.find("#group").attr("value" , group);

        var units = fetchValues(ndbno);
        $.each(units, function(key, value) {
            template.find('#unit')
                    .append($("<option></option>")
                    .attr("value",value.unit)
                    .text(value.unit));
        });

        template.removeAttr('style');
        template.attr("id" , ndbno);
        template.find("#unit").on("change",function(){
            var no = $(this).attr("data-ndb");
            var index = this.selectedIndex;
            setValues(no,index);
        });
        return template;
    }

    //// HELPER FUNCTIONS
    function setValues(ndbno,index){
        var valuesOfIndex = fetchValues(ndbno)[index];
        var target = $("#"+ndbno);
        target.find("#unit").attr("value" , valuesOfIndex.unit);
        target.find("#en").attr("value" , valuesOfIndex.en);
        target.find("#carb").attr("value" , valuesOfIndex.carb);
        target.find("#prot").attr("value" , valuesOfIndex.prot);
        target.find("#fat").attr("value" , valuesOfIndex.fat);
    }
    function fetchValues(ndbno){
        var rv  = [];

        var target_ndb = null;
        for(var i = 0; i < added_ingredient_values.length ; i++){
            if(added_ingredient_values[i].ndbno == ndbno){
                target_ndb = added_ingredient_values[i];
                break;
            }
        }

        for(var i = 0; i< target_ndb.nutrients.length ; i++){
            if(target_ndb.nutrients[i].name == "Energy" && target_ndb.nutrients[i].unit == "kcal" ){
                for(var j = 0 ; j<target_ndb.nutrients[i].measures.length ; j++){
                    var obj = {unit:"nan",en:-1,carb:-1,prot:-1,fat:-1};
                    obj.en = target_ndb.nutrients[i].measures[j].value;
                    rv[j] = obj;
                }
            }
            if(target_ndb.nutrients[i].name == "Carbohydrate, by difference"){
                for(var j = 0 ; j<target_ndb.nutrients[i].measures.length ; j++){
                    rv[j].carb = target_ndb.nutrients[i].measures[j].value;
                }
            }
            if(target_ndb.nutrients[i].name == "Protein"){
                for(var j = 0 ; j<target_ndb.nutrients[i].measures.length ; j++){
                    rv[j].prot = target_ndb.nutrients[i].measures[j].value;
                }
            }
            if(target_ndb.nutrients[i].name == "Total lipid (fat)"){
                for(var j = 0 ; j<target_ndb.nutrients[i].measures.length ; j++){
                    rv[j].fat = target_ndb.nutrients[i].measures[j].value;
                    rv[j].unit = target_ndb.nutrients[i].measures[j].label;
                }

            }
        }
        return rv;

    }
    function removeValues(ndbno){
        for(var i = 0 ; i<added_ingredient_values.length ; i++){
            if(added_ingredient_values[i].ndbno == ndbno){
                added_ingredient_values.splice(i);
                break;
            }
        }
    }







});

