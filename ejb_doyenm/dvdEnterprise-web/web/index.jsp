<%-- 
    Document   : dvdInfo
    Created on : 28 nov. 2015, 17:33:53
    Author     : doyenm
--%>


<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DVD Information</title>
        <link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <a href="forwardCartItemInfo.jsp">Information about my cart item</a>
        <div class="row">
            <div class="col-lg-6">
                <div class="container">

                    <form action="./DVDServlet" method="POST" class="form-horizontal">
                        <fieldset>

                            <!-- Form Name -->
                            <legend>Add a new DVD:</legend>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="title">Title (*)</label>  
                                <div class="col-md-2">
                                    <input name="title" type="text" placeholder="Title" class="form-control input-md"/>

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="genre">Genre</label>  
                                <div class="col-md-2">
                                    <input name="genre" type="text" placeholder="Genre" class="form-control input-md"/>

                                </div>
                            </div>

                            <!-- Password input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="year">Release year(*)</label>
                                <div class="col-md-2">
                                    <input name="year" type="text" placeholder="Release year" class="form-control input-md"/>
                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="stock">Stock(*)</label>  
                                <div class="col-md-2">
                                    <input name="stock" type="text" placeholder="Stock" class="form-control input-md"/>

                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label">Supplier(*)</label>  
                                <div class="col-md-2">
                                    <input name="supplier" type="text" placeholder="Supplier" class="form-control input-md"/>

                                </div>
                            </div>
                            <p>(*) champs obligaoires</p>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input  class="btn btn-success btn btn-success" type="submit" value="Add" name="action"/>
                                    <input  class="btn btn-success btn btn-success" type="submit" value="Edit" name="action"/>
                                    <input  class="btn btn-success btn btn-success" type="submit" value="Delete" name="action"/>
                                    <input  class="btn btn-success btn btn-success" type="submit" value="Search" name="action"/>

                                </div>


                            </div>

                        </fieldset>
                    </form>
                </div>


                <form action="./CartItemServlet" method="POST">

                    Research's results
                    <table border="1" class="table-striped">
                        <th>Identifier</th>
                        <th>Title</th>
                        <th>Genre</th>
                        <th>Year</th>
                        <th>Stock</th>
                        <th>Supplier</th>
                            <c:forEach items="${researchDVD}" var="dvd">
                            <tr>
                                <td>${dvd.idDVD}</td>
                                <td>${dvd.title}</td>
                                <td>${dvd.genre}</td>
                                <td>${dvd.year}</td>
                                <td>${dvd.stock}</td>
                                <td>${dvd.supplier.supplier}</td>

                                <td><button type="submit" name="action" value="${dvd.idDVD}" class="btn btn-success btn btn-success">Add to my cart item</button></td>
                            </tr>
                        </c:forEach>
                    </table>   
                </form>

                <br>
                All DVDs
                <br>
                <form action="./DVDServlet" method="POST">
                    <input type="text" name="title" style="display: none"/></td>
                    <input type="text" name="genre" style="display: none"/>
                    <input type="text" name="year" style="display: none"/>
                    <input type="text" name="stock" style="display: none"/>
                    <input type="text" name="supplier"style="display: none"/>

                    <input type="submit" name="action" value="Display" class="btn btn-success btn btn-success"/>
                </form>
                <form action="./CartItemServlet" method="POST">

                    <table border="1" class="table-striped">
                        <th>Identifier</th>
                        <th>Title</th>
                        <th>Genre</th>
                        <th>Year</th>
                        <th>Stock</th>
                        <th>Supplier</th>
                        <th></th>
                            <c:forEach items="${allDVDs}" var="dvd">
                            <tr>
                                <td>${dvd.idDVD}</td>
                                <td>${dvd.title}</td>
                                <td>${dvd.genre}</td>
                                <td>${dvd.year}</td>
                                <td>${dvd.stock}</td>
                                <td>${dvd.supplier.supplier}</td>
                                <td><button type="submit" name="action" value="${dvd.idDVD}" class="btn btn-success btn btn-success">Add to my cart item</button></td>
                            </tr>
                        </c:forEach>
                    </table>  
                </form>
            </div>

            <div class="container">

                <form action="./IndividualServlet" method="POST" class="form-horizontal">
                    <fieldset>

                        <!-- Form Name -->
                        <legend>Add a new Individual:</legend>

                        <!-- Password input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="first Name">First name(*)</label>
                            <div class="col-md-2">
                                <input name="firstName" type="text" placeholder="first name" class="form-control input-md"/>
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" >Last name(*)</label>  
                            <div class="col-md-2">
                                <input name="lastName" type="text" placeholder="last name" class="form-control input-md"/>

                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input  class="btn btn-success btn btn-success" type="submit" value="Add" name="action"/>
                                <input  class="btn btn-success btn btn-success" type="submit" value="Delete" name="action"/>
                                <input  class="btn btn-success btn btn-success" type="submit" value="Search" name="action"/>

                            </div>


                        </div>

                    </fieldset>

                    Research's results
                    <table border="1" class="table-striped">
                        <th>Identifier</th>
                        <th>First name</th>
                        <th>Last name</th>
                            <c:forEach items="${researchIndividual}" var="indiv">
                            <tr>
                                <td>${indiv.idIndividual}</td>
                                <td>${indiv.firstName}</td>
                                <td>${indiv.lastName}</td>
                            </tr>
                        </c:forEach>
                    </table>        

                    <br>
                    All Individuals
                    <br>
                    <input type="submit" name="action" value="Display" class="btn btn-success btn btn-success"/>
                    <table border="1" class="table-striped">
                        <th>Identifier</th>
                        <th>First name</th>
                        <th>Last name</th>
                            <c:forEach items="${allIndividuals}" var="indivAll">
                            <tr>
                                <td>${indivAll.idIndividual}</td>
                                <td>${indivAll.firstName}</td>
                                <td>${indivAll.lastName}</td>
                            </tr>
                        </c:forEach>
                    </table>  
                </form>
            </div>

            <c:forEach items="${researchIndividual}" var="indiv">
                <tr>
                    <td>${indiv.idIndividual}</td>
                    <td>${indiv.firstName}</td>
                    <td>${indiv.lastName}</td>
                </tr>
            </c:forEach>
        </table>      
    </div>

</div>



<!--
    INFORMATIONS ABOUT PARTICIPATIONS

-->


<div class="row">
    <div class="col-lg-12">
        <form action="./ParticipationServlet" method="POST" class="form-horizontal">

            <div class="container">

                <fieldset>

                    <!-- Form Name -->
                    <legend>Add a new Participation:</legend>

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Title">Title(*)</label>
                        <div class="col-md-2">
                            <input name="title" type="text" placeholder="title" class="form-control input-md"/>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Release year(*)</label>  
                        <div class="col-md-2">
                            <input name="year" type="text" placeholder="release year" class="form-control input-md"/>

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="first Name">First name(*)</label>
                        <div class="col-md-2">
                            <input name="firstName" type="text" placeholder="first name" class="form-control input-md"/>
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Last name(*)</label>  
                        <div class="col-md-2">
                            <input name="lastName" type="text" placeholder="last name" class="form-control input-md"/>

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input  class="btn btn-success btn btn-success" type="submit" value="Add" name="action"/>
                            <input  class="btn btn-success btn btn-success" type="submit" value="Search" name="action"/>

                        </div>


                    </div>

                </fieldset>


                Research's results
                <table border="1" class="table-striped">
                    <th>DVD's title</th>
                    <th>DVD's release year</th>
                    <th>Individual's first name</th>
                    <th>individual's last name</th>
                        <c:forEach items="${researchParticipations}" var="particip">
                        <tr>
                            <td>${particip.title}</td>
                            <td>${particip.year}</td>
                            <td>${particip.firstName}</td>
                            <td>${particip.lastName}</td>

                        </tr>
                    </c:forEach>
                </table>        

                <br>
                All Participations
                <br>
                <input type="submit" name="action" value="Display" class="btn btn-success btn btn-success"/>
                <table border="1" class="table-striped">
                    <th>DVD's title</th>
                    <th>DVD's release year</th>
                    <th>Individual's first name</th>
                    <th>individual's last name</th>
                        <c:forEach items="${allParticipations}" var="particip">
                        <tr>
                            <td>${particip.title}</td>
                            <td>${particip.year}</td>
                            <td>${particip.firstName}</td>
                            <td>${particip.lastName}</td>

                        </tr>
                    </c:forEach>
                </table>  
        </form>
    </div>
</div>
</div>


</body>
</html>