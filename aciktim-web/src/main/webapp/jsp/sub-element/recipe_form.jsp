<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container">
    <form action="${contextPath}/recipe/${action_type}" method="post" class="form-group">
        <div class="row">
            <div class="input-group">
                <span class="input-group-addon">Recipe Name : </span>
                <input type="text" name="recipe_name" class="form-control" placeholder="e.g. Saksuka" aria-describedby="basic-addon1" value="${existing_recipe_name}">
            </div>
        </div>

        <div class="row">
            <div class="input-group">
                <span class="input-group-addon">Description : </span>
                <input type="text" name="description" class="form-control" placeholder="Description" aria-describedby="basic-addon1" value="${existing_recipe_description}">
            </div>
        </div>

        <div class="row">
            <div class="input-group">
                <span class="input-group-addon">Image Url: </span>
                <input type="text" name="image_url" class="form-control" placeholder="Url" aria-describedby="basic-addon1" value="${existing_recipe_image_url}">
            </div>
        </div>

        <div class="row">
            <div class="input-group">
                <input type="hidden" name="recipe_id" value="${existing_recipe_id}">
                <button type="submit" class="btn btn-success">${action_type}</button>
            </div>

        </div>
    </form>
</div>
