package dictionary

import dictionary.types.HashSetDictionary
import dictionary.types.ListDictionary
import dictionary.types.TreeSetDictionary

class DictionaryProvider {
    companion object{ //ez azert kell, hogy majd a Main-ben lathato legyen a createDictionary, ha ezt a fuggvenyt nem raknam a companion objectbe akkor a Main nem fogja latni ezt a fuggvenyt
        fun createDictionary(type: DictionaryType): IDictionary {
            return when(type){ //itt azert nem kell else ag, mert le van kezelve az osszes enum
                DictionaryType.ARRAY_LIST -> ListDictionary
                DictionaryType.TREE_SET ->  TreeSetDictionary
                DictionaryType.HASH_SET ->  HashSetDictionary
            }
        }
    }

}