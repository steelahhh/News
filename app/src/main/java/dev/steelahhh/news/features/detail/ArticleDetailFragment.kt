package dev.steelahhh.news.features.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dev.steelahhh.news.R
import dev.steelahhh.news.di.injector
import dev.steelahhh.news.di.viewModel
import kotlinx.android.synthetic.main.fragment_article_detail.*

class ArticleDetailFragment : Fragment() {
    private val vm by viewModel { injector.articleDetailViewModel }

    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_article_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.article.observe(viewLifecycleOwner, Observer(::render))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getByTitle(args.title)
    }

    private fun render(article: ArticleUi) {
        Glide.with(requireContext())
            .load(article.image)
            .centerCrop()
            .into(image)
        info.text = article.info
        date.text = article.date
        toolbar.title = article.title
        content.text = article.content
        source.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            startActivity(browserIntent)
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
