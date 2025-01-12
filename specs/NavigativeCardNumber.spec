Specification Heading
=====================
Created by erkandemirbas on 9.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
## TS4_NavigativeCardNumber
   tags:NavigativeCardNumber tags:smoke
  * catchylabs'a login ol
  * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
  * Elementin yüklenmesini bekle "OpenMoneyTransfer"
  * Elementine tıkla "OpenMoneyTransfer"
  * Elementin yüklenmesini bekle "AddMoney"
  * Elementine tıkla "AddMoney"
  * "1234" textini "CardNumber" elemente yaz
  * Elementine tıkla "CardHolder"
  * Element "TooShort" var mı kontrol et yoksa hata mesajı ver "tooShort Texti görülmüyor"
  * "1234233234234343" textini "CardNumber" elemente yaz
  * Element "TooLong" var mı kontrol et yoksa hata mesajı ver "TooLong Texti görülmüyor"
