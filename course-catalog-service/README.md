# Notes

## Initialization
```
>> mkdir course-catalog-service
>> cd course-catalog-service
>> gradle init --type kotlin-application
```

## Run
```
>> gradle build
>> gradle bootRun
```

## Kotlin Knowledge
### JSR-305
```
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
```

It relates to how the Kotlin compiler handles annotations from the javax.annotation package, which is specified by JSR-305. The JSR-305 annotations are used for nullability annotations in Java code (like @Nonnull and @Nullable).

### Version
```
>> kotlinc -version
```

```
>> kotlin -version
```
