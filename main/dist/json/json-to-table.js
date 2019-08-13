var json2table = function (json, classes) {
    var cols = Object.keys(json[0]);

    var headerRow = '';
    var bodyRows = '';

    classes = classes || '';

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    cols.map(function(col) {
        headerRow += '<th>' + capitalizeFirstLetter(col) + '</th>';
    });

    json.map(function(row) {
        bodyRows += '<tr>';

        cols.map(function(colName) {
            var rowInfo ;
            var rowVal =  row[colName];
            /**值是数组*/
            if( rowVal instanceof Array){
                rowInfo = "<ul>";
                rowVal.forEach(function(innerVal,index,array){
                    var innerRowInfo;
                    if (innerVal instanceof Object){ 
                       for(innerKey in innerVal){  
                            innerRowInfo = "<label>"+innerKey+"</label>: <span>"+innerVal[innerKey]+"</span>";
                        }      
                    } 
                    rowInfo += "<li>"+(innerRowInfo || innerVal)+"</li>";
                });
                rowInfo += "</ul>";
            } 
            rowInfo = rowInfo   || row[colName];
            bodyRows += '<td onmouseover="alphaPlay(this)">' + rowInfo + '</td>';
        })

        bodyRows += '</tr>';
    });

    return '<table class="' + classes + '">' +
                '<thead>' +'<tr>' + marked(headerRow) + '</tr>' + '</thead>' +
                '<tbody>' + marked(bodyRows)+ '</tbody>' +
            '</table>';
}

var isAlpha = false;

var json2tableByAlpha = function (json) {
    isAlpha = true;
    return json2table(json,'');
}

var alphaPlay =function (e) {
    if(!isAlpha){
        return;
    }
    var baseUrl = '';
    if(window.location.host.indexOf("localhost") == -1){
        //读取远程
        baseUrl = 'https://raw.githubusercontent.com/runcoding/static/master/wiki';
    }
    var sound = new Howl({
        src: [baseUrl+'/alpha/'+e.innerHTML+'.mp3'],
        volume: 0.5,
        onend: function() {
            //console.log('Finished!');
        }
    });
    sound.play();
}

