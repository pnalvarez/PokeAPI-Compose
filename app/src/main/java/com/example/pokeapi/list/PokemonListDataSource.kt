package com.example.pokeapi.list//package com.example.pokeapi
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import javax.inject.Inject
//
//class PokemonListDataSource @Inject constructor(private val dataRepository: PokemonListRepositoryInterface):PagingSource<Int, List<PokemonListItem>>() {
//    companion object {
//        private const val STARTING_PAGE_INDEX = 1
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, List<PokemonListItem>>): Int? {
//        // We need to get the previous key (or next key if previous is null) of the page
//        // that was closest to the most recently accessed index.
//        // Anchor position is the most recently accessed index
//        return state.anchorPosition?.let { anchorPosition->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
//            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<PokemonListItem>> {
//        val position = params.key ?: STARTING_PAGE_INDEX
//        val response = dataRepository.getPokemonList(position, params.loadSize)
//        if (response.isSuccessful) {
//            val body = response.body()
//            if (body != null) {
//                return LoadResult.Page(
//                    data = body,
//                    prevKey = if (position == STARTING_PAGE_INDEX) null else -1,
//                    nextKey = if (body.isEmpty()) null else position + 1
//                )
//            } else {
//                return return LoadResult.Page(
//                    data = body,
//                    prevKey = if (position == STARTING_PAGE_INDEX) null else -1,
//                    nextKey = if (body.isEmpty()) null else position + 1
//                )
//            }
//        }
//    }
//}