<?xml version="1.0" encoding='UTF-8' ?>
<helpset version="1.0">
    <title> Ayuda aplicacion Gestor de notas</title>
    <maps>
        <homeID>GestorDeNotas</homeID>
        <mapref location="map_file.jhm"/>   
    </maps>

    <view>
         <name>Tabla contenidos</name>
         <label>Tabla de contenidos</label>
         <type>javax.help.TOCView</type>
         <data>toc.xml</data>
    </view>
    <view>
         <name>Indice</name>
         <label>El indice</label>
         <type>javax.help.IndexView</type>
         <data>indice.xml</data>
    </view>
    <view>
        <name>Buscar</name>
        <label>Buscar</label>
        <type>javax.help.SearchView</type>
        <data engine="com.sun.java.help.search.DefaultSearchEngine"> JavaHelpSearch </data>
    </view>
</helpset>
