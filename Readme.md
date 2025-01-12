# Test Otomasyon Projesi

## Genel Bakış
Bu proje, Selenium otomasyon aracı ve Java programlama dili kullanılarak oluşturulmuştur. Maven yapı yönetim aracı olarak kullanılmıştır. Test senaryoları için *Gauge* ve *Extent Reports* ile raporlama sağlanmıştır. Ayrıca etiketleme (tag) yapısı kullanılarak testlerin düzenlenmesi ve belirli testlerin çalıştırılması desteklenmiştir.

## Özellikler
* *Raporlama*:
    - *Gauge*: İnsan tarafından okunabilir raporlar ve spesifikasyona dayalı test yürütme sağlar.
    - *Extent Reports*: Detaylı ve görsel olarak zengin raporlama.
* *Etiketleme (Tagging)*:
    - Testleri etiketleyerek belirli kategorilere göre seçici çalıştırma özelliği.
* *Maven* ile bağımlılık yönetimi ve kolay yapılandırma.

## Kullanılan Bağlantılar
Test senaryolarında kullanılan uygulama bağlantısı:
* [CatchyLabs Web Client](https://catchylabs-webclient.testinium.com/)

## Gereksinimler
Projeyi çalıştırmadan önce aşağıdaki yazılımların sisteminizde kurulu olduğundan emin olun:
1. Java 11 veya üstü
2. Maven
3. Gauge (Kurulum için [Gauge Resmi Belgeleri](https://docs.gauge.org/getting_started.html))

## Proje Yapısı

|-- src
|   |-- main
|   |   |-- java
|   |-- test
|       |-- java
|       |-- resources
|-- pom.xml
|-- README.md


## Kullanılan Senaryolar
Projede aşağıdaki test senaryoları geliştirilmiştir:
1. *AddMoney.spec*: Para ekleme işlemleri için.
2. *BuyerAccountChange.spec*: Kullanıcı hesap bilgilerini değiştirme senaryosu.
3. *CatchylapsSmoke.spec*: Smoke test senaryoları.
4. *EditAccount.spec*: Kullanıcı hesap düzenleme işlemleri için.
5. *NavigativeCardHolder.spec*: Kart sahibi bilgilerini doğrulama.
6. *NavigativeCardNumber.spec*: Kart numarası doğrulama işlemleri.
7. *NavigativeExpiryDate.spec*: Kart son kullanma tarihi doğrulama işlemleri.
8. *TheFinishAccount.spec*: Hesap tamamlanma işlemleri.
9. *Transactions.step.spec*: Finansal işlemler için adım tanımlamaları.
10. *TransferMoney.spec*: Para transfer işlemleri için.

## Kurulum ve Çalıştırma
1. Projeyi klonlayın:
   bash
   git clone https://github.com/your-repo-url.git

2. Proje dizinine geçin:
   bash
   cd project-directory

3. Gerekli bağımlılıkları yüklemek için:
   bash
   mvn clean install


## Testlerin Çalıştırılması
### Maven ile
Tüm testleri çalıştırmak için:
bash
mvn test


Belirli etiketlere sahip testleri çalıştırmak için:
bash
mvn gauge:execute -Dtags="tagName"


Smoke testlerini çalıştırmak için:
bash
mvn gauge:execute -Dflags="--tags smoke"


## Raporlama
* *Gauge Raporları*: Test çalıştırma sonrası reports klasöründe otomatik olarak oluşturulur.
* *Extent Reports*: extent-reports klasöründe bulunur ve HTML formatında raporlar sağlar.

## Proje Yapılandırması
pom.xml dosyasında tanımlanan anahtar bağımlılıklar:
xml
<dependencies>
<dependency>
<groupId>com.thoughtworks.gauge</groupId>
<artifactId>gauge-java</artifactId>
<version>${gauge.version}</version>
<scope>test</scope>
</dependency>
<dependency>
<groupId>org.seleniumhq.selenium</groupId>
<artifactId>selenium-java</artifactId>
<version>${selenium.version}</version>
</dependency>
<dependency>
<groupId>com.aventstack</groupId>
<artifactId>extentreports</artifactId>
<version>4.1.7</version>
</dependency>
</dependencies>


## Katkıda Bulunma
1. Projeyi forklayın.
2. Yeni bir dal oluşturun:
   bash
   git checkout -b feature-branch

3. Değişikliklerinizi yapın ve dalınıza commitleyin.
4. Bir pull request oluşturun.