package edu.boun.cmpe451.group2.controller;

import edu.boun.cmpe451.group2.client.*;
import edu.boun.cmpe451.group2.exception.ExException;
import edu.boun.cmpe451.group2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import sun.util.resources.cldr.aa.CalendarData_aa_ER;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.text.SimpleDateFormat;

@Controller
@Scope("request")
public class RecipeController {

    @Qualifier("userModel")
    @Autowired
    private UserModel userModel = null;
    @Qualifier("recipeModel")
    @Autowired
    private RecipeModel recipeModel = null;

//    /**
//    * @param  model the model that will be sent
//     *@param bad_a string used as a boolean to check bad attempt
//     *@param ownerID the ID of the recipe owner
//     *@param search_keyword the keyword enterd in the search bar
//     *@param ingredients_string string used as list of ingredients to be searched
//     *@param t string used as list of tags to be searched
//     *@param madeAt string indicating the place that recipe was made (i.e. home or restaurant)
//     *@param carbo string used as a double for carbohydrate value
//     *@param fat string used as a double for fat value
//     *@param protein string used as a double for protein value
//     *@param calories string used as a double for calories value
//     *@param session_id string used as the session id of a user
//     *@return recipes page
//     *@throws ExException any possible exception (most probably null pointer exception)
//     */
    @RequestMapping(value = {"/recipes"})
    public String viewrecipes(
            ModelMap model,
            @RequestParam(required = false, defaultValue = "false") String bad_a,

            @RequestParam(required = false, defaultValue = "-1") String ownerID,
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "") String[] ingredients,
            @RequestParam(required = false, defaultValue = "") String[] tags,
            @RequestParam(required = false, defaultValue = "home") String madeAt,

            @RequestParam(required = false, defaultValue = "99999") String carbUpper,
            @RequestParam(required = false, defaultValue = "0") String carbLower,

            @RequestParam(required = false, defaultValue = "99999") String fatUpper,
            @RequestParam(required = false, defaultValue = "0") String fatLower,

            @RequestParam(required = false, defaultValue = "99999") String protUpper,
            @RequestParam(required = false, defaultValue = "0") String protLower,

            @RequestParam(required = false, defaultValue = "99999") String calUpper,
            @RequestParam(required = false, defaultValue = "0") String calLower,

            @CookieValue(value = "session_id", defaultValue = "") String session_id) {
        System.out.println("Ingredient Count : " + ingredients.length);
        System.out.println("Tag Count : " + tags.length);

        model.put("bad_attempt", bad_a);
        model.put("content_bar_selection", "recipes");

        List<Recipe> recipeResults = new ArrayList<>(); // If nothing is found then empty list
        User user;
        if (!session_id.equals("")) { // LOGGED IN NOW
            user = userModel.getUser(session_id);
            model.put("isInst", user.isInst);
            model.put("full_name", user.full_name);

            if(!ownerID.equals("-1")){ // User wants to check his own recipes
                recipeResults = recipeModel.getRecipes(Long.parseLong(user.id));
            }else{ // User searches recipes
                ArrayList<String> ingredient_list = listify(ingredients);
                ArrayList<String> tag_list = listify(tags);
                boolean inst = madeAt.equals("restaurant");

                double calU = Double.parseDouble(calUpper);
                double calL = Double.parseDouble(calLower);

                double carU = Double.parseDouble(carbUpper);
                double carL = Double.parseDouble(carbLower);

                double protU = Double.parseDouble(protUpper);
                double protL = Double.parseDouble(protLower);

                double fatU = Double.parseDouble(fatUpper);
                double fatL = Double.parseDouble(fatLower);

                try {
                    recipeResults = recipeModel.advancedSearchRecipes(keyword,
                                                                            ingredient_list,
                                                                            inst,fatU,carU,protU,calU,
                                                                            fatL,carL,protL,calL,
                                                                            tag_list,
                                                                            Long.parseLong(user.id));
                }catch (ExException e){
                    e.printStackTrace();
                    System.out.println("Shit man");
                }
            }
        }else{ // LOGGED OUT NOW
            model.put("full_name", "");
            try {
                if(keyword.equals("")){
//                    recipeResults = recipeModel.searchRecipes(keyword);
                    recipeResults = recipeModel.searchRecipesRandom(10);
                    Collections.shuffle(recipeResults);
                }else{
                    recipeResults = recipeModel.searchRecipes(keyword);
                }
            }catch (ExException e){
                e.printStackTrace();
            }
        }
        System.out.println("Resipe resült sayz : "+recipeResults.size());
        model.put("recipeResults", recipeResults);
        return "user-views/recipes";

    }


    //################################################
    //####### DISPLAY ONE RECIPE ONSCREEN
    //################################################
//    @RequestMapping(value = {"/recipe/view"})
//    public String recipeview(
//            ModelMap model,
//            @RequestParam String action_type,
//            @RequestParam(required = false, defaultValue = "-1") String recipe_id,
//            @CookieValue(value = "session_id", defaultValue = "") String session_id) {
//
//        if (session_id.equals("")) {
//            return "redirect:index";
//        } else {
//            User user = userModel.getUser(session_id);
//            model.put("full_name", user.full_name);
//            model.put("email", user.email);
//
//            model.put("action_type", action_type);
//
//            model.put("existing_recipe_name", "");
//            model.put("existing_recipe_description", "");
//            model.put("existing_recipe_image_url", "");
//
//            if (action_type.equals("edit")) {
//                Long rp_id = Long.parseLong(recipe_id);
//                try {
//                    Recipe rm = recipeModel.getRecipe(rp_id);
//                    model.put("existing_recipe_id", rp_id);
//                    model.put("existing_recipe_name", rm.name);
//                    model.put("existing_recipe_description", rm.description);
//                    model.put("existing_recipe_image_url", rm.pictureAddress);
//                } catch (Exception e) {
//                    System.out.printf("Bullshit");
//                }
//            }
//        }
//        model.put("content_bar_selection", "recipes");
//        return "recipe-views/recipe_grid";
//    }

    @RequestMapping(value = {"/recipe/view"})
    public String recipeview(
            ModelMap model,
            @RequestParam String action_type,
            @RequestParam(required = false, defaultValue = "-1") String recipe_id,
            @CookieValue(value = "session_id", defaultValue = "") String session_id) {

        if (session_id.equals("")) {
            return "redirect:index";
        } else {
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("isInst", user.isInst);
            model.put("email", user.email);

            model.put("action_type", action_type);

            model.put("existing_recipe_name", "");
            model.put("existing_recipe_description", "");
            model.put("existing_recipe_image_url", "");

            if (action_type.equals("edit")) {
                Long rp_id = Long.parseLong(recipe_id);
                try {
                    Recipe rm = recipeModel.getRecipe(rp_id);
                    model.put("existing_recipe_id", rp_id);
                    model.put("existing_recipe_name", rm.name);
                    model.put("existing_recipe_description", rm.description);
                    model.put("existing_recipe_image_url", rm.pictureAddress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.put("content_bar_selection", "recipes");
        return "recipe-views/recipe_grid";
    }


    //################################################
    //####### RECIPE FORM SCREEN FOR CREATE AND EDIT
    //################################################
    @RequestMapping(value = {"/recipe/form"})
    public String recipeform(
            ModelMap model,
            @RequestParam String action_type,
            @RequestParam(required = false, defaultValue = "-1") String recipe_id,
            @CookieValue(value = "session_id", defaultValue = "") String session_id) {

        User user = null;
        if (session_id.equals("")) {
            return "redirect:index";
        } else {
            user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("isInst", user.isInst);
            model.put("email", user.email);

            model.put("action_type", action_type);

            model.put("existing_recipe_name", "");
            model.put("existing_recipe_description", "");
            model.put("existing_recipe_image_url", "");

            if (action_type.equals("edit")) {
                Long rp_id = Long.parseLong(recipe_id);
                try {
                    Recipe rm = recipeModel.getRecipe(rp_id);
                    model.put("existing_recipe_id", rp_id);
                    model.put("existing_recipe_name", rm.name);
                    model.put("existing_recipe_description", rm.description);
                    model.put("existing_recipe_image_url", rm.pictureAddress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.put("content_bar_selection", "create_recipe");
        return "recipe-views/recipe_form_page";
    }


    //################################################
    //####### DISPLAY ONE RECIPE ONSCREEN
    //################################################
    @RequestMapping(value = {"/recipe/single"})
    public String singlerecipe(
            @RequestParam String recipe_id,
            @CookieValue(value = "session_id", defaultValue = "") String session_id,
            ModelMap model
    ) {

        if (!session_id.equals("")){
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("isInst", user.isInst);

//            if (Long.parseLong(user.id) == recipe.ownerID) {
//                model.put("is_owner", true);
//            } else {
//                model.put("is_owner", false);
//            }
        }else{
            model.put("full_name" , "");
        }
        try {
            Recipe recipe = recipeModel.getRecipe(Long.parseLong(recipe_id));
            model.put("recipe", recipe);
            model.put("owner_name", userModel.getUserByID(recipe.ownerID).full_name);
            model.put("today_day", GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH));
            model.put("today_month", GregorianCalendar.getInstance().get(Calendar.MONTH)+1);
            model.put("today_year", GregorianCalendar.getInstance().get(Calendar.YEAR));

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index";
        }
        return "recipe-views/single_recipe";
    }

    //################################################
    //####### RECIPE CONSUME LOGIC
    //################################################
    @RequestMapping(value = {"/recipe/consume"}, method = RequestMethod.POST)
    public String consume(
            @RequestParam String recipe_id,
            @RequestParam String day,
            @RequestParam String month,
            @RequestParam String year,
            @CookieValue(value = "session_id", defaultValue = "") String session_id,
            ModelMap model
    ) {

        try {
            User user = userModel.getUser(session_id);
            Calendar date = new GregorianCalendar();
            date.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            userModel.consume(Long.parseLong(user.id), Long.parseLong(recipe_id), date);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index";
        }

        return "redirect:/recipes";
    }
    //################################################
    //####### RECIPE ADD LOGIC
    //################################################
    @RequestMapping(value = {"/recipe/add"}, method = RequestMethod.POST)
    public String recipeadd(
            @RequestParam String recipe_name,
            @RequestParam String description,
            @RequestParam String image_url,
            @CookieValue(value = "session_id", defaultValue = "") String session_id,
            ModelMap model,
            HttpServletRequest request) {


        // INGREDIENT METADATA
        String[] ingredient_no = request.getParameterValues("ingredient_ndbno");
        String[] ingredient_name = request.getParameterValues("ingredient_name");
        String[] ingredient_amount = request.getParameterValues("ingredient_amount");
        // INGREDIENT NUTRITIONAL DATA
        String[] ingredient_en = request.getParameterValues("ingredient_en");
        String[] ingredient_carb = request.getParameterValues("ingredient_carb");
        String[] ingredient_prot = request.getParameterValues("ingredient_prot");
        String[] ingredient_fat = request.getParameterValues("ingredient_fat");
        String[] ingredient_unit = request.getParameterValues("ingredient_unit");
        String[] ingredient_group = request.getParameterValues("ingredient_group");
        // TAG DATA
        String[] tag_name = request.getParameterValues("tag_name");
        String[] tag_class = request.getParameterValues("tag_class");


        Long id = Long.parseLong(userModel.getUser(session_id).id);

        try {
            Recipe r = new Recipe();
            r.ownerID = id;

            fillRecipe(r,
                    recipe_name,
                    id,
                    image_url,
                    description,
                    formAmountMap(ingredient_no, ingredient_name, ingredient_amount, ingredient_en, ingredient_carb, ingredient_prot, ingredient_fat, ingredient_unit),
                    formTagList(tag_name, tag_class, ingredient_group, ingredient_name));


            recipeModel.addRecipe(r);
            model.put("type", "SUCCESS");
        } catch (ExException e) {
            e.printStackTrace();
            model.put("type", "ERROR_EX");

        } catch (Exception e) {
            e.printStackTrace();
            model.put("type", "ERROR");

        }

        return "redirect:/recipes";
    }


    //################################################
    //####### RECIPE EDIT LOGIC
    //################################################
    @RequestMapping(value = "recipe/edit")
    public String recipeedit(
            @RequestParam(required = false) Long recipe_id,
            @RequestParam(required = false) String image_url,
            @RequestParam(required = false) String recipe_name,
            @RequestParam(required = false) String description,
            @CookieValue(value = "session_id", defaultValue = "") String session_id,
            ModelMap model) {

        Long userId = Long.parseLong(userModel.getUser(session_id).id);

        try {
            Recipe r = new Recipe();
            r.name = recipe_name;
            r.id = recipe_id;
            r.IngredientList = null;
            r.pictureAddress = image_url;
            r.description = description;
            recipeModel.updateRecipe(r);
            model.put("type", "SUCCESS");
        } catch (Exception e) {
            model.put("type", "ERROR");

        }

        return "redirect:/recipes";
    }




    //################################################
    //####### CONSUMPTION AND RECOMMENDATIONS
    //################################################
    @RequestMapping(value = {"user/recommendations"})
    public String userrecommendations(
            ModelMap model,
            @CookieValue(value="session_id", defaultValue = "") String session_id) {

        if (!session_id.equals("")) {
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("isInst", user.isInst);


            List<Menu> list = new ArrayList<Menu>();
            model.put("recommendations", list);

            model.put("isInst", user.isInst);
            model.put("content_bar_selection", "recommendations");

            return "user-views/recommendations";
        }else {
            return "redirect:/recipes";
        }

    }

    /**
     *  This controller function is triggered when "user/dailyconsumption"
     *  is accessed with a GET or POST method.
     *  A logged in user must exist when the URL is accessed. When not
     *  logged in, there is no anchor leading to this URL.
     *
     *  A date is given as parameter. The default is current date. The
     *  controller retrieves consumed recipes of logged in user at given date
     *  by querying "dailyConsumption" table in the database.
     *
     *  @author Mustafa Onur Eken, i.e. oeken
     *  @version 1.0
     *  @param date Must be in the form of "yyyy/mm/dd", default is current date
     * */

    @RequestMapping(value = {"user/dailyconsumption"})
    public String userdailyconsumption(
            ModelMap model,
            @RequestParam (required = false) String date,
            @CookieValue(value="session_id", defaultValue = "") String session_id) {



        model.put("content_bar_selection", "dailyconsumption");
        if (!session_id.equals("")) {
            User user = userModel.getUser(session_id);
            model.put("full_name", user.full_name);
            model.put("isInst", user.isInst);
            Calendar queriedCal = null;
            if (date == null){
                Date currentDate = new Date();
                queriedCal = Calendar.getInstance();
                queriedCal.setTime(currentDate);
            }else{
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    Date queried_date = formatter.parse(date);
                    queriedCal = Calendar.getInstance();
                    queriedCal.setTime(queried_date);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            try{

                int year = queriedCal.get(Calendar.YEAR);
                int month = queriedCal.get(Calendar.MONTH);
                int day = queriedCal.get(Calendar.DAY_OF_MONTH);

                queriedCal.add(Calendar.MONTH, 1);
                ArrayList<Recipe> recipes = userModel.getDailyConsumption(Long.parseLong(user.id), queriedCal);

                model.put("year", year);
                model.put("month", month);
                model.put("day", day);
                model.put("consumed_recipes", recipes);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "user-views/daily_consumption";
        }else{
            return "redirect:/recipes";
        }

    }


    //################################################
    //####### HELPER FUNCTIONS
    //################################################
    public void fillRecipe(Recipe r,
                           String recipe_name,
                           Long id,
                           String image_url,
                           String description,
                           List<Ingredient> ingredient_map,
                           List<Tag> taglist) {
        r.name = recipe_name;
        r.id = id;
        r.pictureAddress = image_url;
        r.description = description;
        r.setIngredientList(ingredient_map);
        r.tagList = taglist;

    }

    public List<Ingredient> formAmountMap(String[] nos,
                                                   String[] names,
                                                   String[] amounts,
                                                   String[] ens,
                                                   String[] carbs,
                                                   String[] prots,
                                                   String[] fats,
                                                   String[] units) {

        List<Ingredient> m = new LinkedList<>();
        int counter = 0;
        if (names == null) return m;
        for (String no : nos) {
            Ingredient i = new Ingredient();
            i.id = Long.parseLong(nos[counter]);
            i.name = names[counter];
            i.calories = Double.parseDouble(ens[counter]);
            i.carbohydrate = Double.parseDouble(carbs[counter]);
            i.protein = Double.parseDouble(prots[counter]);
            i.fat = Double.parseDouble(fats[counter]);
            i.unitName = units[counter];
            i.amount=(Long.parseLong(amounts[counter]));
            m.add(i);
            counter++;
        }
        return m;
    }

    public List<Tag> formTagList(String[] tag_name, String[] parent_tag, String[] ingredient_groups, String[] ingredient_names) {
        List<Tag> tl = new ArrayList<>();
        int counter = 0;
        if (tag_name == null) return tl;
        for (String tag : tag_name) {
            Tag t = new Tag();
            t.name = tag;
            t.parentTag = parent_tag[counter];
            tl.add(t);
            counter++;
        }

        boolean lactose = false;
        boolean gluten = false;

        for(int i = 0 ; i < ingredient_groups.length ; i++){
            System.out.println("TARRAK DETECTED!!!!");
            String group = ingredient_groups[i];
            String name = ingredient_names[i];
            if (!(name.toLowerCase().contains("gluten-free") || name.toLowerCase().contains("gluten free")) && group.equals("Baked Products")) gluten = true;
            if (!(name.toLowerCase().contains("lactose free")) && group.equals("Dairy and Egg Products")) lactose = true;
        }

        if (lactose){
            System.out.println("LACTOSE DETECTED!!!!");
            Tag t = new Tag();
            t.name = "lactose";
            t.parentTag = "dairy";
            tl.add(t);
        }
        if (gluten){
            System.out.println("GLUTEN DETECTED!!!!");
            Tag t = new Tag();
            t.name = "gluten";
            t.parentTag = "baked";
            tl.add(t);
        }

        return tl;
    }

    public ArrayList<String> listify(String[] stringArray){
        ArrayList<String> rv = new ArrayList<>();
        for (String s : stringArray){
            rv.add(s);
        }
        return rv;
    }

//    public ArrayList<String> listify(String string){
//        ArrayList<String> rv = new ArrayList<>();
//
//        return rv;
//    }



    //################################################
    //####### DEPO FONKSIYONLAR
    //################################################


    //    @RequestMapping(value = "recipe/delete")
//    public String recipedelete(
//            @RequestParam(required = false) Long recipe_id) {
//        recipeModel.getRecipeDao().deleteRecipe(recipe_id);
//        return "redirect:/recipes";
//    }







//    if(carbo.equals("-1"))
//    carbo = String.valueOf(Double.MAX_VALUE);
//    if(fat.equals("-1"))
//    fat = String.valueOf(Double.MAX_VALUE);
//    if(protein.equals("-1"))
//    protein = String.valueOf(Double.MAX_VALUE);
//    if(calories.equals("-1"))
//    calories = String.valueOf(Double.MAX_VALUE);
//
//
//
//
//    if (user != null && ownerID.equals(user.id)) { //bring my recipes
//        List<Recipe> recipes = recipeModel.getRecipes(Long.parseLong(user.id));
//        model.put("recipeResults", recipes);
//    } else {
//        try {
//            if (!search_keyword.equals("-1")) {
//                if (!ingredients_string.equals("-1")) { // bring keyword + ingredients
//                    List<String> ingredientList = Arrays.asList(ingredients_string.split("\\s*,\\s*"));
//                    List<Recipe> recipeResults = recipeModel.searchRecipes(search_keyword, ingredientList);
//
//                    // the tags that will be searched in recipes
//                    if(!t.equals("-1")) {
//                        List<String> tagNameList = Arrays.asList(t.split("\\s*,\\s*"));
//                        ArrayList<Tag> tagList = new ArrayList<Tag>();
//                        for(String tagName : tagNameList) {
//                            ArrayList<Tag> tempList = new ArrayList(recipeModel.getRecipeDao().getTagsByName(tagName));
//                            tagList.removeAll(tempList);
//                            tagList.addAll(tempList);
//                        }
//
//
//                        // filter out the recipe results which do not include any of the tags
//                        for(Iterator<Recipe> iterator = recipeResults.iterator(); iterator.hasNext();) {
//                            Recipe current = iterator.next();
//                            ArrayList<Tag> recipeTagList = new ArrayList(recipeModel.getRecipeDao().getTags(current.getId()));
//
//                            boolean hasAtLeastOne = false;
//                            outerloop:
//                            for(int i=0; i<tagList.size(); i++) {
//                                for(int j=0; j<recipeTagList.size(); j++) {
//                                    if(tagList.get(i).getName().equals(recipeTagList.get(i).getName())) {
//                                        hasAtLeastOne = true;
//                                        break outerloop;
//                                    }
//                                }
//                            }
//                            if(!hasAtLeastOne)
//                                iterator.remove();
//                        }
//                    }
//
//                    // calories limit
//                    for(Iterator<Recipe> iterator = recipeResults.iterator(); iterator.hasNext();) {
//                        Recipe current = iterator.next();
//                        if((current.getTotalCarb() >= Double.parseDouble(carbo) || (current.getTotalFat() >= Double.parseDouble(fat)) ||
//                                (current.getTotalProtein() >= Double.parseDouble(protein)) || (current.getTotalCal() >= Double.parseDouble(calories))))
//                            iterator.remove();
//                    }
//
//
//                    model.put("recipeResults", recipeResults);
//                } else { // bring keyword
//                    List<Recipe> recipeResults = recipeModel.searchRecipes(search_keyword);
//
//                    // list of tags that will be searched in recipes
//                    if(!t.equals("-1")) {
//                        List<String> tagNameList = Arrays.asList(t.split("\\s*,\\s*"));
//                        ArrayList<Tag> tagList = new ArrayList<Tag>();
//                        for(String tagName : tagNameList) {
//                            ArrayList<Tag> tempList = new ArrayList(recipeModel.getRecipeDao().getTagsByName(tagName));
//                            tagList.removeAll(tempList);
//                            tagList.addAll(tempList);
//                        }
//
//
//                        // filter out the recipe results which do not include any of the tags
//                        for(Iterator<Recipe> iterator = recipeResults.iterator(); iterator.hasNext();) {
//                            Recipe current = iterator.next();
//                            ArrayList<Tag> recipeTagList = new ArrayList(recipeModel.getRecipeDao().getTags(current.getId()));
//
//                            boolean hasAtLeastOne = false;
//                            outerloop:
//                            for(int i=0; i<tagList.size(); i++) {
//                                for(int j=0; j<recipeTagList.size(); j++) {
//                                    if(tagList.get(i).getName().equals(recipeTagList.get(i).getName())) {
//                                        hasAtLeastOne = true;
//                                        break outerloop;
//                                    }
//                                }
//                            }
//                            if(!hasAtLeastOne)
//                                iterator.remove();
//                        }
//                    }
//
//                    // calories limit
//                    for(Iterator<Recipe> iterator = recipeResults.iterator(); iterator.hasNext();) {
//                        Recipe current = iterator.next();
//                        if((current.getTotalCarb() >= Double.parseDouble(carbo) || (current.getTotalFat() >= Double.parseDouble(fat)) ||
//                                (current.getTotalProtein() >= Double.parseDouble(protein)) || (current.getTotalCal() >= Double.parseDouble(calories))))
//                            iterator.remove();
//                    }
//
//                    model.put("recipeResults", recipeResults);
//                }
//            } else { // bring random recipes
//                List<Recipe> recipeResults = recipeModel.searchRecipesRandom(10);
//                System.out.println("Tag List : "+recipeResults.get(0).getTagList().size());
//                System.out.println("Themselves : "+recipeResults.get(0).getTagList());
//
//                model.put("recipeResults", recipeResults);
//            }
//        } catch (ExException e) {
//            e.printStackTrace();
//        }
//    }
//
//    model.put("bad_attempt", bad_a);
//    model.put("content_bar_selection", "recipes");


}