<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>
<div id="page">
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">
                    <asset:image src="grails.svg" alt="Grails Logo"/>
                </a>
            </div>

            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <g:render template="/common/topbar"/>
                </ul>
            </div>
        </div>
    </div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <div class="page-header text-center">
        <h1>Collabo Todo</h1>
    </div>

    <div id="content">
        <g:layoutBody/>
    </div>

    <div id="sidebar">
        %{--<g:render template="/common/buddies"/>--}%
    </div>

    <div id="footer" class="footer" role="contentinfo">
        <g:render template="/common/footer"/>
    </div>

    <asset:javascript src="application.js"/>
</div>
</body>
</html>
