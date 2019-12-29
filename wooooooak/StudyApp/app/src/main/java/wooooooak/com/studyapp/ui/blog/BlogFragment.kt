package wooooooak.com.studyapp.ui.blog

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.data.model.database.AppDataBase
import wooooooak.com.studyapp.data.model.datasource.local.NaverLocalDataSourceImpl
import wooooooak.com.studyapp.data.model.repository.NaverApiRepositoryImpl
import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.sharedpreference.SharedPreferneceManager
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter
import wooooooak.com.studyapp.ui.base.ItemSearchFragment

class BlogFragment : ItemSearchFragment<Blog>(R.layout.fragment_blog) {

    override val adapter = BlogSearchListAdapter(object : BaseSearchListAdapter.ItemListener<Blog> {
        override fun loadMoreItems(list: List<Blog>, index: Int) {
            lifecycleScope.launch {
                presenter.fetchMoreItems(list, index)
            }
        }

        override fun renderWebView(url: String) {
            requireContext().startWebView(url)
        }
    })

    private val presenter by lazy {
        BlogPresenter(
            this, NaverApiRepositoryImpl(
                NaverLocalDataSourceImpl(SharedPreferneceManager(requireContext()), AppDataBase(requireContext()))
            )
        )
    }

    override fun initItemsByTitle(title: String, cached: Boolean) {
        lifecycleScope.launch {
            presenter.fetchItemsWithNewTitle(title, cached)
        }
    }

}
