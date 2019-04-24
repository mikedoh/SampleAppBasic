package com.example.sampleappbasic

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import githubrepos.data.GitHubRepository
import githubrepos.data.RepoListState
import githubrepos.data.RepoListState.Loading
import githubrepos.data.RepoListState.Loaded
import githubrepos.data.RepoListState.Error
import githubrepos.domain.GitHubOrganizationReposUseCase
import githubrepos.presentation.RepoListViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.inOrder
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.verification.Times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepoListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule() //to work with arch component

    @Mock
    lateinit var useCase: GitHubOrganizationReposUseCase

    @Mock
    lateinit var observer: Observer<RepoListState>

    lateinit var testOBject: RepoListViewModel

    private val validRepoList = listOf(
        GitHubRepository(stargazers_count = 0),
        GitHubRepository(stargazers_count = 1),
        GitHubRepository(stargazers_count = 2)
    )

    private val DEFAULT_ORG_NAME = "DEFAULT_ORG_NAME"

    @Before
    fun setup() {
        testOBject = RepoListViewModel(useCase)
        testOBject.getRepoList().observeForever(observer)
    }

    @Test
    fun `Given valid result from useCase, when launch getTopThreeReposByStars(),then Loaded state will emit with valid result`() {
        //Given
        Mockito.`when`(useCase.getOrganizationRepos(any())).thenReturn(Single.just(validRepoList))

        val sortedRepoList = validRepoList.sortedWith(
            object : Comparator<GitHubRepository> {
                override fun compare(o1: GitHubRepository, o2: GitHubRepository): Int {
                    return o2.stargazers_count - o1.stargazers_count
                }
            }
        )
        //When
        testOBject.getTopThreeReposByStars(DEFAULT_ORG_NAME)

        //Then
        Mockito.verify(observer, Times(1)).onChanged(eq(Loading))
        Mockito.verify(observer, Times(1)).onChanged(eq(Loaded(sortedRepoList)))
        Mockito.verifyNoMoreInteractions(observer)
    }

    @Test
    fun `Given valid result from useCase, when launch getTopThreeReposByStars(),then Loading and Loaded state will emit in order`() {
        //Given
        Mockito.`when`(useCase.getOrganizationRepos(any())).thenReturn(Single.just(validRepoList))

        val sortedRepoList = validRepoList.sortedWith(
            object : Comparator<GitHubRepository> {
                override fun compare(o1: GitHubRepository, o2: GitHubRepository): Int {
                    return o2.stargazers_count - o1.stargazers_count
                }
            }
        )
        //When
        testOBject.getTopThreeReposByStars(DEFAULT_ORG_NAME)

        //Then
        inOrder(observer) {
            Mockito.verify(observer, Times(1)).onChanged(eq(Loading))
            Mockito.verify(observer, Times(1)).onChanged(eq(Loaded(sortedRepoList)))
        }
    }

    @Test
    fun `Given error from useCase, when launch getTopThreeReposByStars(),then Error state will emit`() {
        //Given
        val error = Throwable()
        Mockito.`when`(useCase.getOrganizationRepos(any())).thenReturn(Single.error(error))

        //When
        testOBject.getTopThreeReposByStars(DEFAULT_ORG_NAME)

        //Then
        Mockito.verify(observer, Times(1)).onChanged(eq(Loading))
        Mockito.verify(observer, Times(1)).onChanged(eq(Error(error)))
        Mockito.verifyNoMoreInteractions(observer)
    }

    @Test
    fun `Given repoList with less than two repos returned from useCase, when launch getTopThreeReposByStars(),then Loaded state will emit with valid result`() {
        //Given
        val repoListSizeTwo = listOf(
            GitHubRepository(stargazers_count = 0),
            GitHubRepository(stargazers_count = 1)
        )
        Mockito.`when`(useCase.getOrganizationRepos(any())).thenReturn(Single.just(repoListSizeTwo))
        val sortedRepoList = repoListSizeTwo.sortedWith(
            object : Comparator<GitHubRepository> {
                override fun compare(o1: GitHubRepository, o2: GitHubRepository): Int {
                    return o2.stargazers_count - o1.stargazers_count
                }
            }
        )

        //When
        testOBject.getTopThreeReposByStars(DEFAULT_ORG_NAME)

        //Then
        Mockito.verify(observer, Times(1)).onChanged(eq(Loading))
        Mockito.verify(observer, Times(1)).onChanged(eq(Loaded(sortedRepoList)))
        Mockito.verifyNoMoreInteractions(observer)
    }

    @Test
    fun `Given empty List returned from useCase, when launch getTopThreeReposByStars(),then Loaded state will emit with empty list`() {
        //Given
        Mockito.`when`(useCase.getOrganizationRepos(any())).thenReturn(Single.just(emptyList()))

        //When
        testOBject.getTopThreeReposByStars(DEFAULT_ORG_NAME)

        //Then
        Mockito.verify(observer, Times(1)).onChanged(eq(Loading))
        Mockito.verify(observer, Times(1)).onChanged(eq(Loaded(emptyList())))
        Mockito.verifyNoMoreInteractions(observer)
    }
}