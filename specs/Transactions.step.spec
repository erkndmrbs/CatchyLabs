Specification Heading
=====================
Created by erkandemirbas on 9.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
 ## TS6_TranssactionMoney
  tags:TranssactionMoney tags:smoke
 * catchylabs'a login ol
 * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
 * Elementin yüklenmesini bekle "OpenMoneyTransfer"
 * Elementine tıkla "OpenMoneyTransfer"
  * "MyAccount" elementini kontrol et
  * Elementin yüklenmesini bekle "TransferMoney"
  * Elementine tıkla "TransferMoney"
  * "100" textini "AmountTransfer" elemente yaz
  * "AmountTransfer" elementinin textini "TransactionMoney" olarak kaydet
  * Elementine tıkla "Send"
  * Elementin yüklenmesini bekle "Amount"
  * "2" saniye bekle
  * "Transfer" elementi "100" değerini içeriyor mu kontrol et


