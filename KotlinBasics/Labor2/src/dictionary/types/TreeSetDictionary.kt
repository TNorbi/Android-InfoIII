package dictionary.types

import dictionary.IDictionary
import java.io.File
import java.util.*

object TreeSetDictionary: IDictionary {
    val words = TreeSet<String>()

    init{ //ez az init lesz a konstruktor tulajdonkeppen
        if(File(IDictionary.fileName).isFile){
            File(IDictionary.fileName).readLines().forEach{ add(it) } //ez az add gyakorlatilag az az add fuggveny, amit mi overrideoltunk(sajat fuggvenyet hivja meg az interfacebol)
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
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }
}