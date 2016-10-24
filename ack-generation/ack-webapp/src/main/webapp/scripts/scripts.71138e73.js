"use strict";angular.module("webappApp",["ngAnimate","ngAria","ngCookies","ngMessages","ngResource","ngRoute","ngSanitize","ngTouch","ngTreetable","ui.bootstrap"]).directive("convertToNumber",function(){return{require:"ngModel",link:function(a,b,c,d){d.$parsers.push(function(a){return a?parseInt(a,10):null}),d.$formatters.push(function(a){return a?""+a:null})}}}).filter("sanitize",["$sce",function(a){return function(b){return a.trustAsHtml(b)}}]),angular.module("webappApp").controller("MainCtrl",function(){this.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}),angular.module("webappApp").controller("AboutCtrl",function(){this.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}),angular.module("webappApp").directive("matrixEditor",function(){return{templateUrl:"me",restrict:"E",scope:{matrix:"=",headers:"="},bindToController:!0,controllerAs:"meCtrl",controller:["$scope","ngTreetableParams","matrixHandler",function(a,b,c){var d=this;this.clear=c.clearCategory,this.getValue=function(a,b){return a.indexes&&a.indexes.hasOwnProperty(b)?a.indexes[b]:""},this.setToWPath=function(a,b,c,e){for(var f in e){var g=e[f];if(g.hasOwnProperty("path")&&g.path===a)return void(g.hasOwnProperty("indexes")&&g.indexes.hasOwnProperty(b)&&-1!==g.indexes[b]&&(g.indexes[b]=c));g.hasOwnProperty("children")&&g.children&&g.children.length>0&&d.setToWPath(a,b,c,g.children)}},this.setToWUsage=function(a,b,c,e){for(var f in e){var g=e[f];g.hasOwnProperty("usage")&&g.usage===a&&g.hasOwnProperty("indexes")&&g.indexes.hasOwnProperty(b)&&-1!==g.indexes[b]&&(g.indexes[b]=c),g.hasOwnProperty("children")&&g.children&&g.children.length>0&&d.setToWUsage(a,b,c,g.children)}},this.edit=function(){"usage"===d.a?(console.log("using U"),console.log(d.usageS),console.log(d.categ),console.log(d.setTo),d.setToWUsage(d.usageS,d.categ,d.setTo,d.matrix)):"path"===d.a&&(console.log("using P"),console.log(d.path),console.log(d.categ),console.log(d.setTo),d.setToWPath(d.path,d.categ,d.setTo,d.matrix))},this.dynamic_params=new b({getNodes:function(a){return a&&a.children?a.children:d.matrix},getTemplate:function(){return"tree_node"}}),this.isExpandable=function(a){return a&&a.hasOwnProperty("children")&&a.children?a.children.length>0:!1},this.labelClass=function(a){return a&&a.hasOwnProperty("type")?"label-"+angular.lowercase(a.type):""},this.labelText=function(a){return a&&a.hasOwnProperty("type")?a.type.charAt(0):""},this.cellClass=function(a){return"t"+a},a.$watch(function(){return d.matrix},function(){d.dynamic_params.refresh()})}]}}),angular.module("webappApp").service("matrixHandler",["$q","$http",function(a,b){var c={getMatrix:function(){var c=a.defer();return b.get("/ack-webapp/api/matrix").success(function(a){c.resolve(a)}),c.promise},clearCategory:function(a,b){console.log("TET");for(var d in a){var e=a[d];e.hasOwnProperty("indexes")&&e.indexes.hasOwnProperty(b)&&-1!==e.indexes[b]&&(e.indexes[b]=0),e.hasOwnProperty("children")&&e.children&&e.children.length>0&&c.clearCategory(e.children,b)}}};return c}]),angular.module("webappApp").controller("IntegrationcontrollerCtrl",["matrixHandler","$scope","$rootScope","$http",function(a,b,c,d){b.mtx=[],b.headers=[],b.cotrls={clear:a.clearCategory,aaa:"fff"};var e=this,f=0;this.setActive=function(a){f=a},this.isActive=function(a){return f===a},b.validate=function(){e.message=b.editor1.getValue("\n"),b.matrixS={message:{id:"1",desc:"desc",children:b.mtx},header:b.headers},console.log(this.message),d.post("/ack-webapp/api/validate",{matrix:b.matrixS,message:e.message},{}).then(function(a){b.rsp=a.data.content,b.report=a.data.report,b.editor2.setValue(b.rsp),console.log(a.data.content)})},this.initCodemirror=function(){b.editor1=CodeMirror.fromTextArea(document.getElementById("cfTextArea1"),{lineNumbers:!0,fixedGutter:!0,theme:"elegant",readOnly:!1,mode:"hl7v2",showCursorWhenSelecting:!0,gutters:["CodeMirror-linenumbers","cm-edi-segment-name"]}),b.editor2=CodeMirror.fromTextArea(document.getElementById("cfTextArea2"),{lineNumbers:!0,fixedGutter:!0,theme:"elegant",readOnly:!1,mode:"hl7v2",showCursorWhenSelecting:!0,gutters:["CodeMirror-linenumbers","cm-edi-segment-name"]})},a.getMatrix().then(function(a){b.mtx=a.message.children,b.headers=a.header})}]),angular.module("webappApp").run(["$templateCache",function(a){a.put("views/about.html","<p>This is the about view.</p>"),a.put("views/header.html",'<div class="navbar navbar-default navbar-static-top" role="navigation"> <div class="brand" id="appheader"> <a style="display: inline-block; text-shadow:none" href="#"> <div id="apptitle">NIST <span style="color:white">Ack Generation</span><span style="color:red; font-size:12px"> 1.0.0-SNAPSHOT</span></div> </a> </div> <div class="navbar-collapse collapse" id="appnavi"> <ul class="nav navbar-nav"> <li ng-class="{\'active\':isActive(\'/home\')}"><a href="#/home"><i class="fa fa-home"></i> Home</a></li> </ul> </div> </div>'),a.put("views/main.html",'<div class="jumbotron"> <h1>\'Allo, \'Allo!</h1> <p class="lead"> <img src="images/yeoman.42092f92.png" alt="I\'m Yeoman"><br> Always a pleasure scaffolding your apps. </p> <p><a class="btn btn-lg btn-success" ng-href="#/">Splendid!<span class="glyphicon glyphicon-ok"></span></a></p> </div> <div class="row marketing"> <h4>HTML5 Boilerplate</h4> <p> HTML5 Boilerplate is a professional front-end template for building fast, robust, and adaptable web apps or sites. </p> <h4>Angular</h4> <p> AngularJS is a toolset for building the framework most suited to your application development. </p> <h4>Karma</h4> <p>Spectacular Test Runner for JavaScript.</p> </div>')}]);