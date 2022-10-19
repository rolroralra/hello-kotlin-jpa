package com.kotlin.jpa.repository.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.test.annotation.Rollback
import java.util.*
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.streams.toList

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class JpaRepositoryTests<T: Any, ID: Any> {

    private val INIT_ENTITY_TOTAL_COUNT = 50
    private val DEFAULT_PAGEABLE_SIZE = 20

    protected abstract fun repository(): JpaRepository<T, ID>
    protected abstract fun createTestInstance(): T
    protected abstract fun idFromEntity(entity: T): ID

    fun providePageableArguments() : Stream<Arguments> {
        return IntStream.rangeClosed(0, INIT_ENTITY_TOTAL_COUNT / DEFAULT_PAGEABLE_SIZE + 1)
            .mapToObj{ Arguments.of(it, DEFAULT_PAGEABLE_SIZE) }
    }

    @BeforeEach
    fun beforeEach() {
        IntStream.rangeClosed(1, INIT_ENTITY_TOTAL_COUNT)
            .mapToObj { createTestInstance() }
            .forEach(repository()::save)
    }

    @Test
    fun findById() {
        // given
        val entity = createTestInstance()
        val savedEntity = repository().save(entity)

        // when
        val findById = repository().findById(idFromEntity(savedEntity))

        // then
        assertThat(findById)
            .isNotEmpty
            .get()
            .isEqualTo(savedEntity)
    }

    @ParameterizedTest
    @MethodSource(value = ["providePageableArguments"])
    fun findAll(page: Int, size: Int) {
        // given
        val expectedEntityList = repository().findAll().stream()
            .skip((page.toLong()) * size)
            .limit(size.toLong())
            .toList()

        val pageRequest = PageRequest.of(page, size)

        // when
        val result = repository().findAll(pageRequest)

        // then
        assertThat(result.content)
            .containsExactlyElementsOf(expectedEntityList)
    }

    @Test
    fun save() {
        // given
        val entity = createTestInstance()

        // when
        val savedEntity = repository().save(entity)

        // then
        assertThat(savedEntity)
            .isNotNull
    }

    @Test
    fun delete() {
        // given
        val entity = createTestInstance()
        val savedEntity = repository().save(entity)

        // when
        repository().deleteById(idFromEntity(savedEntity))
        val optionalUser = repository().findById(idFromEntity(savedEntity))

        // then
        assertThat(optionalUser).isEmpty
    }
}

