package dictionary.types

import dictionary.IDictionary
import java.io.File

object ListDictionary:IDictionary {
    //words azert lathato public, mert a val miatt ezt nem lehet kesobb modositani,ezert a private is elmaradhatott

    val words = arrayListOf<String>()

    init{ //ez az init lesz a konstruktor tulajdonkeppen
        if(File(IDictionary.fileName).isFile){
            File(IDictionary.fileName).readLines().forEach{add(it)} //ez az add gyakorlatilag az az add fuggveny, amit mi overrideoltunk(sajat fuggvenyet hivja meg az interfacebol)
        }

    }

    override fun add(word: String): Boolean {
        if(!find(word)){
            words.add(word)
            return true
        }
        return false
    }

    override fun find(word: String): Boolean {
        //return words.any{it==word}
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }

}