---
title: Laboratorio de Programación Orientada a Objetos
author: Ezequiel Cohn
---

El enunciado del laboratorio se encuentra en [este link](https://docs.google.com/document/d/1wLhuEOjhdLwgZ4rlW0AftgKD4QIPPx37Dzs--P1gIU4/edit#heading=h.xe9t6iq9fo58).

# 1. Tareas
Pueden usar esta checklist para indicar el avance.

## Verificación de que pueden hacer las cosas.
- [x] Java 17 instalado. Deben poder compilar con `make` y correr con `make run` para obtener el mensaje de ayuda del programa.

## 1.1. Interfaz de usuario
- [x] Estructurar opciones
- [x] Construir el objeto de clase `Config`

## 1.2. FeedParser
- [x] `class Article`
    - [x] Atributos
    - [x] Constructor
    - [x] Método `print`
    - [x] _Accessors_
- [x] `parseXML`

## 1.3. Entidades nombradas
- [x] Pensar estructura y validarla con el docente
- [x] Implementarla
- [x] Extracción
    - [x] Implementación de heurísticas
- [x] Clasificación
    - [x] Por tópicos
    - [x] Por categorías
- Estadísticas
    - [x] Por tópicos
    - [x] Por categorías
    - [x] Impresión de estadísticas

## 1.4 Limpieza de código
- [x] Pasar un formateador de código
- [x] Revisar TODOs

# 2. Experiencia
En este proyecto de Java, creé un clasificador de entidades usando un diccionario en formato JSON provisto por la catedra, y utilicé expresiones regulares para identificar categorías como personas, lugares, organizaciones y eventos. Modularicé el codigo siguiendo las practicas del POO.

# 3. Preguntas
1. Explicar brevemente la estructura de datos elegida para las entidades nombradas.

   Para las entidades nombradas, se usó una estructura de clases donde cada tipo de entidad (como Person, Location, Organization, Event, y Other) hereda de una clase base llamada NamedEntity. Esta clase base tiene atributos comunes a todas las entidades, como la categoría, los temas y los atributos extra. Luego, cada clase específica define cómo obtener el nombre de la entidad. Esto hace que sea fácil manejar distintos tipos de entidades y agregar más tipos en el futuro si es necesario.

2. Explicar brevemente cómo se implementaron las heurísticas de extracción.
   
   Las heurísticas de extracción se implementaron usando patrones y diccionarios. Para una de las heurísticas, por ejemplo, se busca identificar posibles entidades basadas en palabras capitalizadas en el texto, ignorando ciertas palabras que están en un diccionario de exclusión. Se utiliza una expresión regular para encontrar secuencias de palabras que comienzan con mayúscula, y si no están en el diccionario de exclusión, se consideran como candidatos. Así se filtran y extraen las posibles entidades del texto.

# 4. Extras
Completar si hacen algo.