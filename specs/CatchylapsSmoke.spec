Specification Heading
=====================
Created by erkandemirbas on 10.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
## TS1_AddMoney
   tags:AddMoney
  * catchylabs'a login ol
  * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
  * Elementin yüklenmesini bekle "OpenMoneyTransfer"
  * Elementine tıkla "OpenMoneyTransfer"
  * Elementin yüklenmesini bekle "AddMoney"
  * Elementine tıkla "AddMoney"
  * "1234 1234 1234 1234" textini "CardNumber" elemente yaz
  * "CardHolder" elementine "Otomasyon" değerini js ile yaz
  * "ExpiryDate" elementine "1026" değerini js ile yaz
  * "CVV" elementine "110" değerini js ile yaz
  * "AddMoneyAmount" elementine "100" değerini js ile yaz
  * Elementine tıkla "Add"
  * Elementin yüklenmesini bekle "Amount"
  /* "Amount" li elementin text degeri hafizada "TotalAccount" olarak saklanan text ile esit mi kontrol et

  ## TS2_EditAccount
    tags:EditAccount
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
     



  ## TS3_NavigativeCardHolder
   tags:NavigativeCardHolder
  * catchylabs'a login ol
  * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
  * Elementin yüklenmesini bekle "OpenMoneyTransfer"
  * Elementine tıkla "OpenMoneyTransfer"
  * Elementin yüklenmesini bekle "AddMoney"
  * Elementine tıkla "AddMoney"
  * Elementine tıkla "CardHolder"
  * "sdf" textini "CardHolder" elemente tek tek yaz
  * Elementine tıkla "ExpiryDate"
  * Element "TooShort" var mı kontrol et yoksa hata mesajı ver "tooShort Texti görülmüyor"


   ## TS4_NavigativeCardNumber
   tags:NavigativeCardNumber
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

  ## TS5_NavigativeExpiryDate
     tags:NavigativeExpiryDate
   * catchylabs'a login ol
   * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
   * Elementin yüklenmesini bekle "OpenMoneyTransfer"
   * Elementine tıkla "OpenMoneyTransfer"
   * Elementin yüklenmesini bekle "AddMoney"
   * Elementine tıkla "AddMoney"
   * "2343" değerini "ExpiryDate" elementine focus ile yaz
   * Elementine tıkla "Add"
   * Element "WrongDate" var mı kontrol et yoksa hata mesajı ver "WrongDate Texti görülmüyor"

    ## TS6_TranssactionMoney
      tags:TranssactionMoney
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

     ## TS7_TransferMoney
      tags:TransferMoney
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

    ## TS8_BuyerAccountChange
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
     ## TS9_FinishTheAccount
      tags:FinishTheAccount tags:smoke
      * catchylabs'a login ol
      * Şuanki URL "https://catchylabs-webclient.testinium.com/" değerini içeriyor mu kontrol et
      * Elementin yüklenmesini bekle "OpenMoneyTransfer"
      * Elementine tıkla "OpenMoneyTransfer"
       * "MyAccount" elementini kontrol et
       * "Amount" elementinin textini "TotalAccount" olarak kaydet
       * Elementin yüklenmesini bekle "TransferMoney"
       * Elementine tıkla "TransferMoney"
       * "TotalAccount" değişkenini "AmountTransfer" elementine yaz
       * Elementine tıkla "Send"
       * Elementin yüklenmesini bekle "Amount"
       * "2" saniye bekle
       * "Amount" elementi "0" değerini içeriyor mu kontrol et
       * Hesaba para yatir