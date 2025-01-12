Specification Heading
=====================
Created by erkandemirbas on 12.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
## TS7_BuyerAccountChange
    tags:BuyerAccountChange tags:smoke
   * catchylabs'a login ol
   * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
   * Elementin yüklenmesini bekle "OpenMoneyTransfer"
   * Elementine tıkla "OpenMoneyTransfer"
   * "MyAccount" elementini kontrol et
   * "Amount" elementinin textini "TotalAccount" olarak kaydet
   * Elementine tıkla "TransferMoney"
   * Elementine tıkla "HesapList"
   * "HesapList" olarak comboboxdan bir değer seçilir
   * popupa gec
   * "100" textini "AmountTransfer" elemente yaz
   * Elementine tıkla "Send"
   * Elementin yüklenmesini bekle "Amount"
   * Element "ReceiverAccount" var mı kontrol et yoksa hata mesajı ver "Element mevcut"



