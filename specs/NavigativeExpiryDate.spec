Specification Heading
=====================
Created by erkandemirbas on 9.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
## TS5_NavigativeExpiryDate
   tags:NavigativeExpiryDate tags:smoke
  * catchylabs'a login ol
  * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
  * Elementin yüklenmesini bekle "OpenMoneyTransfer"
  * Elementine tıkla "OpenMoneyTransfer"
  * Elementin yüklenmesini bekle "AddMoney"
  * Elementine tıkla "AddMoney"
  * "2343" değerini "ExpiryDate" elementine focus ile yaz
  * Elementine tıkla "Add"
  * Element "WrongDate" var mı kontrol et yoksa hata mesajı ver "WrongDate Texti görülmüyor"