
Specification Heading
=====================
Created by erkandemirbas on 8.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

  ## TS7_TransferMoney
  tags:TransferMoney tags:smoke
 * catchylabs'a login ol
 * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
 * Elementin yüklenmesini bekle "OpenMoneyTransfer"
 * Elementine tıkla "OpenMoneyTransfer"
  * "MyAccount" elementini kontrol et
  * "Amount" elementinin textini "TotalAccount" olarak kaydet
  * Elementin yüklenmesini bekle "TransferMoney"
  * Elementine tıkla "TransferMoney"
  * "100" textini "AmountTransfer" elemente yaz
  * Elementine tıkla "Send"
  * Elementin yüklenmesini bekle "Amount"
  * "2" saniye bekle
  * "Amount" li elementin text degeri hafizada "TotalAccount" olarak saklanan text ile Esit Olmadığını ve farkın doğru olduğunu kontrol et






