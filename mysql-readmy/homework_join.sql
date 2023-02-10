select * from world.country;

select * from world.city;

select * from world.countrylanguage;

# task_1
select
  c.name country_name,
  cl.language langusge
from world.country c
join world.countrylanguage cl on c.code = cl.CountryCode;

# task_2
select
  c.name country_name,
  count(cl.language) count_languages
from world.country c
join world.countrylanguage cl on c.code = cl.countryCode
group by country_name
having count_languages > 1
order by count_languages desc;

# task_3_1
select
  c.name city_name
from world.city c
join world.countrylanguage cl on c.countryCode = cl.countryCode
where cl.language in ('English');

# task_3_2
select
  c.name city_name,
  cl.percentage percentage
from world.city c
join world.countrylanguage cl on c.countryCode = cl.countryCode
where cl.Language in ('Ukrainian')
order by percentage desc;

# task_4_1
select
  country.name country_name,
  city.name city_name
from world.country country
join world.city city on city.countryCode = country.code
join world.countrylanguage cl on cl.countryCode = city.countryCode
where cl.Language in ('Spanish');

# task_4_2
select
  country.name country_name,
  city.name city_name
from world.country country
join world.city city on city.countryCode = country.code
join world.countrylanguage cl on cl.countryCode = city.countryCode
where cl.Language in ('Ukrainian');

 # task_5
select * from world.author;
select * from world.author_dublicate;

select * from world.author a
left join world.author_dublicate ad on a.author_id = ad.author_id
union
select * from world.author a
right join world.author_dublicate ad on a.author_id = ad.author_id;

select * from world.author a
left join world.author_dublicate ad on a.author_id = ad.author_id
union all
select * from world.author a
right join world.author_dublicate ad on a.author_id = ad.author_id;