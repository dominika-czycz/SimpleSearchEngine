# Simple search engine
> A simple search engine in Java. The search engine is implemented
as an inverted index (http://en.wikipedia.org/wiki/Inverted_index) that runs in memory and can return a result
list that is sorted by TF-IDF (http://en.wikipedia.org/wiki/Tf*idf).
                                                                                                            
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Example](#example)
* [Demo](#demo)
* [Contact](#contact)


## General info 
>The search engine:
* is able to take in a list of documents
* support searches for single terms in the document set
* returns a list of matching documents together with their TF-IDF score
* supports sorting by TF-IDF


## Technologies
* Java 17
* Junit 5
* Maven

## Example
The following documents are indexed:
* Document 1: “the brown fox jumped over the brown dog”
* Document 2: “the lazy brown dog sat in the corner”
* Document 3: “the red fox bit the lazy dog”

A search for “brown” should return the list: [document 1, document 2].
A search for “fox” should return the list: [document 3, document 1].

## Demo
In console enter a term to search and the result list (document id and score) will be displayed. 

## Contact
Created by [@dominika-czycz](https://github.com/dominika-czycz)
 
[dominika-czycz@gmail.com](dominika.czycz@gmail.com) _feel free to contact!_

