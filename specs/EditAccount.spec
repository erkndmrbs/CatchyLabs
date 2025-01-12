Specification Heading
=====================
Created by erkandemirbas on 8.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     

## TS2_EditAccount
   tags:EditAccount tags:smoke
   * catchylabs'a login ol
   * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
   * Elementin yüklenmesini bekle "OpenMoneyTransfer"
   * Elementine tıkla "OpenMoneyTransfer"
   * Elementin yüklenmesini bekle "EditAccount"
   * Elementine tıkla "EditAccount"
   * "Name" elementinin textini "NewName" olarak kaydet
   * "AccountName" elementinin text alanını temizle
   * "AccountName" elementine random değer yaz
   * Elementine tıkla "UPDATE"
   * "2" saniye bekle
   * "Name" li elementin text degeri "NewName" olarak saklanan text ile Esit Olmadığını kontrol et
