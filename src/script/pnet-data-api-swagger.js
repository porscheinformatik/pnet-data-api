import "./../asset/pnet-data-api-swagger.css";

var SwaggerUIBundle = require("swagger-ui-dist").SwaggerUIBundle;
var SwaggerUIStandalonePreset = require("swagger-ui-dist").SwaggerUIStandalonePreset;

const ui = SwaggerUIBundle({
    spec: require("./../swagger/pnet-data-api-swagger.json"),
    dom_id: "#swagger-ui",
    presets: [SwaggerUIBundle.presets.apis, SwaggerUIStandalonePreset],
    layout: "StandaloneLayout",
});
