package bismillah.arabskiy.mualimussaniy.ui.aytiw

import bismillah.arabskiy.mualimussaniy.data.dao.ArticlesDao

class AytiwPresenter(private val dao: ArticlesDao, private val view: AytiwView) {
    fun getAllArticle(id: Int) {
        dao.getArticleById(id)?.let { view.setAllArticle(it) }
    }
}