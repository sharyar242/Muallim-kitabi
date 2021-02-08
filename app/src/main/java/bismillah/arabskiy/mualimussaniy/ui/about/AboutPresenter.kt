package bismillah.arabskiy.mualimussaniy.ui.about

import bismillah.arabskiy.mualimussaniy.data.dao.ArticlesDao

class AboutPresenter(private val dao: ArticlesDao, private val view: AboutView) {
    fun getAllArticle(id: Int) {
        dao.getArticleById(id)?.let { view.setAllArticle(it) }
    }
}