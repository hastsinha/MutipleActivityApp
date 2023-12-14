package com.example.mutipleactivityapp.modal

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Responses(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("schema")
	val schema: String? = null,

	@field:SerializedName("userPinCode")
	val userPinCode: String? = null,

	@field:SerializedName("redirectUrl")
	val redirectUrl: Any? = null,

	@field:SerializedName("keywords")
	val keywords: String? = null,

	@field:SerializedName("ampHtml")
	val ampHtml: Any? = null,

	@field:SerializedName("prev")
	val prev: Any? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("totalCount")
	val totalCount: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("categoryTagForFiltersReset")
	val categoryTagForFiltersReset: String? = null,

	@field:SerializedName("designItems")
	val designItems: List<DesignItemsItem?>? = null,

	@field:SerializedName("seoLinks")
	val seoLinks: List<SeoLinksItem?>? = null,

	@field:SerializedName("showStoreBanner")
	val showStoreBanner: Any? = null,

	@field:SerializedName("showSaleBanner")
	val showSaleBanner: Boolean? = null,

	@field:SerializedName("seoText")
	val seoText: String? = null,

	@field:SerializedName("productPageExperimentEnable")
	val productPageExperimentEnable: Boolean? = null,

	@field:SerializedName("filters")
	val filters: List<FiltersItem?>? = null,

	@field:SerializedName("canonical")
	val canonical: String? = null,

	@field:SerializedName("filtersResetUrl")
	val filtersResetUrl: String? = null,

	@field:SerializedName("selectedTags")
	val selectedTags: Any? = null,

	@field:SerializedName("header")
	val header: String? = null,

	@field:SerializedName("shareUrl")
	val shareUrl: String? = null,

	@field:SerializedName("robotsMeta")
	val robotsMeta: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
): Serializable

data class DesignItemsItem(

	@field:SerializedName("customizationId")
	val customizationId: Int? = null,

	@field:SerializedName("discountPercent")
	val discountPercent: Int? = null,

	@field:SerializedName("makingChargeDiscountValue")
	val makingChargeDiscountValue: Int? = null,

	@field:SerializedName("categoryType")
	val categoryType: String? = null,

	@field:SerializedName("productPageUrl")
	val productPageUrl: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: Long? = null,

	@field:SerializedName("designName")
	val designName: String? = null,

	@field:SerializedName("discountName")
	val discountName: String? = null,

	@field:SerializedName("default")
	val jsonMemberDefault: Boolean? = null,

	@field:SerializedName("discountedPrice")
	val discountedPrice: String? = null,

	@field:SerializedName("flatDiscountPercent")
	val flatDiscountPercent: Int? = null,

	@field:SerializedName("mediaItems")
	val mediaItems: MediaItems? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("designId")
	val designId: Int? = null,

	@field:SerializedName("shortDesc")
	val shortDesc: String? = null,

	@field:SerializedName("discountOnDiamondPriceValue")
	val discountOnDiamondPriceValue: Int? = null,

	@field:SerializedName("discountValue")
	val discountValue: String? = null,

	@field:SerializedName("designCode")
	val designCode: String? = null,

	@field:SerializedName("skuCode")
	val skuCode: String? = null
) : Serializable

data class FilterItemListItem(

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("selected")
	val selected: Boolean? = null
) : Serializable

data class SeoLinksItem(

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class ImageItemsItem(

	@field:SerializedName("urls")
	val urls: Urls? = null,

	@field:SerializedName("greyRender")
	val greyRender: Boolean? = null
) :  Serializable

data class MediaItems(

	@field:SerializedName("videoItem")
	val videoItem: VideoItem? = null,

	@field:SerializedName("carouselSeq")
	val carouselSeq: List<CarouselSeqItem?>? = null,

	@field:SerializedName("greyRender")
	val greyRender: Boolean? = null,

	@field:SerializedName("imageItems")
	val imageItems: List<ImageItemsItem?>? = null
) : Serializable

data class FiltersItem(

	@field:SerializedName("filterItemList")
	val filterItemList: List<FilterItemListItem?>? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("priority")
	val priority: Int? = null
) : Serializable

data class UrlMap(

	@field:SerializedName("414")
	val jsonMember414: String? = null,

	@field:SerializedName("828")
	val jsonMember828: String? = null,

	@field:SerializedName("video_url")
	val videoUrl: String? = null,

	@field:SerializedName("poster_url")
	val posterUrl: String? = null
): Serializable

data class VideoItem(

	@field:SerializedName("poster")
	val poster: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Serializable

data class CarouselSeqItem(

	@field:SerializedName("urlMap")
	val urlMap: UrlMap? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Serializable

data class Urls(

	@field:SerializedName("414")
	val jsonMember414: String? = null,

	@field:SerializedName("828")
	val jsonMember828: String? = null
): Serializable
