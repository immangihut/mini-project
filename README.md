# Mini Project
Contact: immanuel.mangihut@gmail.com
## Endpoints
add new brand first
```sh
POST http://localhost:8080/brand/add
```
```sh
{
    "brandName": "TOYOTA"
}
```
add new car with brand
```sh
POST http://localhost:8080/car/add
```
```sh
{
    "carName": "Innova",
    "brandId": "0053d158-2afa-4419-a8d5-cd7119305342"
}
```
get list of car
```sh
GET http://localhost:8080/car
```
get list of car by specific brand
```sh
GET http://localhost:8080/car/brand=TOYOTA
```
## Database Schema

Brand Table:
| Column | Description |
| ------ | ----------- |
| brand_id  | UUID (Primary Key, Not Null, Unique)  |
| brand_name| VARCHAR(250) (Not Null)              |
Car Table:
| Column | Description |
| ------ | ----------- |
| car_id    | UUID (Primary Key, Not Null, Unique)  |
| car_name  | VARCHAR(250) (Not Null)              |
| brand_id  | UUID (Foreign Key to Brand Table)    |

Relationship:
- Dalam hubungan One-to-Many, satu merek mobil (brand) dapat memiliki banyak mobil. Hal ini direpresentasikan oleh foreign key brand_id di tabel car yang mengacu ke brand_id di tabel brand. Dalam contoh yang diberikan, tabel brand memiliki satu kolom brand_id yang merupakan Primary Key. Tabel car memiliki satu kolom brand_id yang merupakan Foreign Key yang mengacu ke kolom brand_id di tabel brand. Hal ini berarti bahwa setiap baris di tabel brand dapat dikaitkan dengan banyak baris di tabel car, tetapi setiap baris di tabel car hanya dapat dikaitkan dengan satu baris di tabel brand.
- Annotasi @ManyToOne dalam CarEntity menunjukkan bahwa setiap entitas CarEntity mengacu pada satu entitas BrandEntity, namun setiap entitas BrandEntity dapat diacu oleh banyak entitas CarEntity. Jadi, secara sederhana, anotasi @ManyToOne menyatakan bahwa setiap mobil (CarEntity) memiliki satu merek (BrandEntity) saja, tetapi satu merek (BrandEntity) dapat dimiliki oleh banyak mobil (CarEntity).

