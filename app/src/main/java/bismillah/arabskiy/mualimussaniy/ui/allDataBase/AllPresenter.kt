package bismillah.arabskiy.mualimussaniy.ui.allDataBase

import bismillah.arabskiy.mualimussaniy.data.dao.ArticlesDao

class AllPresenter(private val dao: ArticlesDao, private val view: AllView) {
    fun getAllArticle(id: Int) {
        dao.getArticleById(id)?.let { view?.setAllArticle(it) }
    }
}