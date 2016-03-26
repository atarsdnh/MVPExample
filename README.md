# MVPExample
An Android example implement MVP pattern

MVP心得筆記

以下為"個人"實作MVP的心得與方式，
並沒有絕對正確的作法，僅是提供給大家參考

1.MVP簡介

Model:處理資料
View:處理畫面，使用者操作事件通知Presenter
Presenter:畫面和資料的橋樑
(接收使用者操作事件、資料存取結果，依據情境操作View和Model去做對的事情)

2.實作用法

基本設計原則:
Model和Presenter維持純JAVA物件，把Android元件隔離在外面，
需要用到Android元件的話(startService、sendBrocast、startActivity...etc)，
使用View去實作，或是把Android元件包在自定義的Class傳進來操作。

關於request API:
需要get或post資料可以在Model裡面實作，
Presenter在適當時機操作Model傳輸資料，
利用自定義的listener讓Presenter得到資料傳輸結果。

3.小結

優點:
MVP架構用在Android上的好處是為了提升軟體品質，
如果設計的好可以提升可讀性、可維護性、以及Android最缺乏的可測試性(單元測試)

缺點:
對軟體設計概念不熟的新人需要學習成本導入，
需要自己定義MVP，增加額外類別
(或許可以用方便實作MVP的lib，我沒使用過，不過覺得自己寫彈性比較大)

整體使用來說覺得優點遠大於缺點，如果對範例有問題歡迎來信
atarsdng@gmail.com

範例講解實作影片:
https://www.youtube.com/edit?o=U&video_id=RTmhBLc6B-U
